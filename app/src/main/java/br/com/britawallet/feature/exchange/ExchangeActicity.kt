package br.com.britawallet.feature.exchange

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import br.com.britawallet.R
import br.com.britawallet.base.extensions.ItemMenuOptions
import br.com.britawallet.base.extensions.injectPresenter
import br.com.britawallet.base.extensions.setSafeOnClickListener
import br.com.britawallet.base.extensions.showMessageDialog
import br.com.britawallet.base.extensions.showRadioDialog
import br.com.britawallet.base.extensions.toCurrency
import br.com.britawallet.core.Library
import br.com.britawallet.data.model.Currency
import br.com.britawallet.data.model.User
import kotlinx.android.synthetic.main.activity_exchange.*
import kotlinx.android.synthetic.main.wallet_toolbar.view.*

class ExchangeActivity : AppCompatActivity(), ExchangeContract.View {

    override val presenter by injectPresenter(this)

    private lateinit var fromCurrency: Currency
    private lateinit var toCurrency: Currency

    companion object {

        private const val EXCHANGE_REQUEST_CODE = 13000

        private operator fun invoke(context: Context): Intent {
            return Intent(context, ExchangeActivity::class.java)
        }

        fun startActivityForResult(activity: Activity) {
            val intent = ExchangeActivity(activity)
            activity.startActivityForResult(intent, EXCHANGE_REQUEST_CODE)
        }

        fun isOrigin(requestCode: Int) = EXCHANGE_REQUEST_CODE == requestCode
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exchange)

        setupViews()
        presenter.setupTransaction()
    }

    override fun showLoading() {
        excFrLoading.isVisible = true
    }

    override fun hideLoading() {
        excFrLoading.isVisible = false
    }

    override fun showBalance(balance: User.Balance) {
        val currencyResource = balance.toCurrency().toResource()
        excToolbar.wtTxtBalance.text = balance.quantity.toCurrency(currencyResource.symbol.str)
    }

    override fun changeValueFrom(value: Double) {
        excEdtFrom.setText(value.toCurrency(), false)
        attMessageTransaction()
    }

    override fun changeValueTo(value: Double) {
        excEdtTo.setText(value.toCurrency(), false)
        attMessageTransaction()
    }

    override fun changeCurrencyFrom(currency: Currency) {
        fromCurrency = currency
        excImgFrom.setImageResource(currency.toResource().button)
    }

    override fun changeCurrencyTo(currency: Currency) {
        toCurrency = currency
        excImgTo.setImageResource(currency.toResource().button)
    }

    override fun showSuccessfulTransaction() {
        showMessageDialog(R.string.exchange_success.str,
            confirmAction = {
                presenter.onConfirmTransaction()
            }
        )
    }

    override fun close() {
        setResult(Activity.RESULT_OK)
        finish()
    }

    override fun showFatalErrorMessage(message: String) {
        showMessageDialog(message)
    }

    override fun showInsufficientFundsMessage() {
        showMessageDialog(R.string.exchange_insufficient_funds.str)
    }

    private fun setupViews() {
        excBtnFrom.setSafeOnClickListener {
            showRadioDialog(
                items = Currency.all,
                selectedItem = fromCurrency,
                title = R.string.exchange_select_currency.str,
                positiveButton = ItemMenuOptions(
                    1,
                    R.string.exchange_select_currency,
                    ::changeFromCurrency
                ),
                transformLabel = {
                    Library.getResource(it).name.str
                }

            )
        }

        excBtnTo.setSafeOnClickListener {
            showRadioDialog(
                items = Currency.all,
                selectedItem = toCurrency,
                title = R.string.exchange_select_currency.str,
                positiveButton = ItemMenuOptions(
                    1,
                    R.string.exchange_select_currency,
                    ::changeToCurrency
                ),
                transformLabel = {
                    Library.getResource(it).name.str
                }

            )
        }

        excEdtFrom.onValueChange = {
            presenter.calculateTo(fromCurrency, toCurrency, it)
        }

        excEdtTo.onValueChange = {
            presenter.calculateFrom(fromCurrency, toCurrency, it)
        }

        excBtnExchange.setOnClickListener {
            presenter.switchCurrency(fromCurrency, toCurrency, excEdtFrom.value, excEdtTo.value)
        }

        excBtnConfirm.setSafeOnClickListener {
            showMessageDialog(
                R.string.exchange_confirme_message.str,
                cancel = android.R.string.cancel.str,
                confirmAction = {
                    presenter.onExchange(fromCurrency, toCurrency, excEdtFrom.value, excEdtTo.value)
                }
            )
        }
    }

    private fun attMessageTransaction() {
        excTxtMessage.text = getString(
            R.string.exchange_transaction_message,
            excEdtTo.value.toCurrency(),
            toCurrency.toResource().pluralName.str,
            excEdtFrom.value.toCurrency(),
            fromCurrency.toResource().pluralName.str
        )
    }

    private fun changeFromCurrency(currency: Currency) {
        presenter.changeFromCurrency(
            fromCurrency,
            toCurrency,
            excEdtFrom.value,
            excEdtTo.value,
            currency
        )
    }

    private fun changeToCurrency(currency: Currency) {
        presenter.changeToCurrency(
            fromCurrency,
            toCurrency,
            excEdtFrom.value,
            excEdtTo.value,
            currency
        )
    }

    private fun Currency.toResource() =
        Library.getResource(this)

    private fun User.Balance.toCurrency() = Currency[this.currencyType]

    private val Int.str
        get() = getString(this)
}