package br.com.britawallet.feature.exchange

import br.com.britawallet.base.extensions.ddMMyyyy
import br.com.britawallet.base.extensions.launch
import br.com.britawallet.data.local.StaticResources
import br.com.britawallet.data.model.Currency
import br.com.britawallet.data.model.Transaction
import br.com.britawallet.data.model.whenever
import br.com.britawallet.data.remote.QuotationRepository
import br.com.britawallet.data.remote.TransactionRemoteRepository
import br.com.britawallet.data.remote.UserRemoteRepository
import kotlinx.coroutines.CoroutineDispatcher
import java.util.*

class ExchangePresenter(
    override var view: ExchangeContract.View,
    internal val staticResources: StaticResources,
    internal val quotationRepository: QuotationRepository,
    internal val userRemoteRepository: UserRemoteRepository,
    internal val transactionRemoteRepository: TransactionRemoteRepository,
    private val dispatcher: CoroutineDispatcher
) : ExchangeContract.Presenter {

    override fun setupTransaction() {
        val from = Currency.Default
        val to = Currency.all.first { !it.isDefault }

        setupBalance()

        if (Currency.isLoaded) {
            setupQuotation(from, to)
        } else {
            loadQuotation(from, to)
        }
    }

    override fun changeFromCurrency(
        from: Currency,
        to: Currency,
        fromValue: Double,
        toValue: Double,
        new: Currency
    ) {
        if (new == to) {
            switchCurrency(from, to, fromValue, toValue)
            return
        }

        view.changeCurrencyFrom(new)
        calculateFrom(new, to, toValue)
    }

    override fun changeToCurrency(
        from: Currency,
        to: Currency,
        fromValue: Double,
        toValue: Double,
        new: Currency
    ) {
        if (new == from) {
            switchCurrency(from, to, fromValue, toValue)
            return
        }

        view.changeCurrencyTo(new)
        calculateTo(from, new, fromValue)
    }

    override fun switchCurrency(
        from: Currency,
        to: Currency,
        fromValue: Double,
        toValue: Double
    ) {
        view.changeCurrencyFrom(to)
        view.changeCurrencyTo(from)
        view.changeValueFrom(toValue)
        view.changeValueTo(fromValue)
    }

    override fun onExchange(from: Currency, to: Currency, fromValue: Double, toValue: Double) {
        val user = staticResources.user ?: return

        val fromBalance = user.wallet.first { it.currencyType == from.type }
        val toBalance = user.wallet.first { it.currencyType == to.type }

        //check Funds
        if (fromValue > fromBalance.quantity) {
            view.showInsufficientFundsMessage()
            return
        }

        // remove to wallet
        fromBalance.quantity -= fromValue
        // add to wallet
        toBalance.quantity += toValue

        dispatcher.launch {
            userRemoteRepository.attUserWallet(user)
            transactionRemoteRepository.save(
                Transaction(
                    to.type,
                    from.type,
                    toValue,
                    fromValue,
                    Date().ddMMyyyy(),
                    user.login
                )
            )

            view.showSuccessfulTransaction()
        }
    }

    override fun onConfirmTransaction() {
        view.close()
    }

    override fun calculateFrom(from: Currency, to: Currency, toValue: Double) {
        view.changeValueFrom(from.convert(toValue, to))
    }

    override fun calculateTo(from: Currency, to: Currency, fromValue: Double) {
        view.changeValueTo(to.convert(fromValue, from))
    }

    private fun setupBalance() {
        staticResources.user?.let { user ->
            val balance = user.wallet.first { balance ->
                Currency[balance.currencyType].isDefault
            }

            view.showBalance(balance)
        }
    }

    private fun setupQuotation(from: Currency, to: Currency) {
        view.changeCurrencyFrom(from)
        view.changeCurrencyTo(to)

        view.changeValueFrom(from.convert(1.0, to))
        view.changeValueTo(1.0)
    }

    private fun loadQuotation(from: Currency, to: Currency) {
        view.showLoading()

        dispatcher.launch {
            quotationRepository.getQuotation().whenever(
                isBody = {
                    setupQuotation(from, to)
                    view.hideLoading()
                },
                isError = {
                    view.hideLoading()
                    view.showFatalErrorMessage(it)
                }
            )
        }
    }
}
