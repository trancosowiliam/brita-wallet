package br.com.britawallet.feature.history

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.britawallet.R
import br.com.britawallet.base.extensions.injectPresenter
import br.com.britawallet.base.extensions.toCurrency
import br.com.britawallet.core.Library
import br.com.britawallet.data.model.Currency
import br.com.britawallet.data.model.Transaction
import br.com.britawallet.data.model.User
import br.com.britawallet.view.SimpleDividerItemDecoration
import kotlinx.android.synthetic.main.activity_history.*
import kotlinx.android.synthetic.main.wallet_toolbar.view.*

class HistoryActivity : AppCompatActivity(), HistoryContract.View {
    override val presenter by injectPresenter(this)

    private val historyAdapter by lazy { HistoryAdapter() }

    companion object {
        operator fun invoke(context: Context): Intent {
            return Intent(context, HistoryActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        setupViews()
        presenter.loadBalance()
        presenter.loadHistory()
    }

    override fun showBalance(balance: User.Balance) {
        val currencyResource = balance.toCurrency().toResource()
        traToolbar.wtTxtBalance.text = balance.quantity.toCurrency(currencyResource.symbol.str)
    }

    override fun showHistory(transactions: List<Transaction>) {
        historyAdapter.data = transactions.map {
            it.toData()
        }
    }

    private fun setupViews() {
        traRecWallet.layoutManager = LinearLayoutManager(this)
        traRecWallet.addItemDecoration(SimpleDividerItemDecoration(this))
        traRecWallet.adapter = historyAdapter
    }

    private fun Currency.toResource() =
        Library.getResource(this)

    private fun User.Balance.toCurrency() = Currency[this.currencyType]

    private val Int.str
        get() = getString(this)

    private fun Transaction.toData(): TransactionHistoryData {
        val sellResource = Currency[this.sellType].toResource()
        val buyResource = Currency[this.buyType].toResource()

        val sellName = sellResource.name.str
        val buyName = buyResource.name.str

        val sellSymbol = sellResource.symbol.str
        val buySymbol = buyResource.symbol.str

        return TransactionHistoryData(
            sellResource.button,
            buyResource.button,
            getString(R.string.transaction_description, sellName, buyName),
            this.date,
            getString(
                R.string.transaction_value_description,
                sellSymbol,
                sell.toCurrency(),
                sellName
            ),
            getString(R.string.transaction_value_description, buySymbol, buy.toCurrency(), buyName)

        )
    }
}