package br.com.britawallet.feature.exchange

import br.com.britawallet.base.BasePresenter
import br.com.britawallet.base.BaseView
import br.com.britawallet.data.model.Currency
import br.com.britawallet.data.model.User

interface ExchangeContract {
    interface View : BaseView<Presenter> {
        fun showLoading()
        fun hideLoading()

        fun showBalance(balance: User.Balance)
        fun changeValueFrom(value: Double)
        fun changeValueTo(value: Double)

        fun changeCurrencyFrom(currency: Currency)
        fun changeCurrencyTo(currency: Currency)

        fun showSuccessfulTransaction()
        fun close()
        fun showFatalErrorMessage(message:String)
        fun showInsufficientFundsMessage()
    }

    interface Presenter : BasePresenter<View> {
        fun setupTransaction()

        fun changeFromCurrency(
            from: Currency,
            to: Currency,
            fromValue: Double,
            toValue: Double,
            new: Currency
        )

        fun changeToCurrency(
            from: Currency,
            to: Currency,
            fromValue: Double,
            toValue: Double,
            new: Currency
        )

        fun switchCurrency(
            from: Currency,
            to: Currency,
            fromValue: Double,
            toValue: Double
        )

        fun calculateFrom(from: Currency, to: Currency, toValue: Double)
        fun calculateTo(from: Currency, to: Currency, fromValue: Double)

        fun onExchange(from: Currency, to: Currency, fromValue: Double, toValue: Double)
        fun onConfirmTransaction()
    }
}