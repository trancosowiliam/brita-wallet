package br.com.britawallet.feature.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.britawallet.R
import br.com.britawallet.base.extensions.injectPresenter
import br.com.britawallet.base.extensions.setSafeOnClickListener
import br.com.britawallet.base.extensions.toCurrency
import br.com.britawallet.core.Library
import br.com.britawallet.data.model.Currency
import br.com.britawallet.data.model.User
import br.com.britawallet.feature.exchange.ExchangeActivity
import br.com.britawallet.feature.history.HistoryActivity
import br.com.britawallet.feature.profile.ProfileActivity
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.bottom_menu_home.view.*

class HomeActivity : AppCompatActivity(), HomeContract.View {
    override val presenter by injectPresenter(this)
    private val walletAdapter by lazy { WalletAdapter() }

    companion object {
        operator fun invoke(context: Context): Intent {
            return Intent(context, HomeActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setupViews()
        presenter.loadWallet()
    }

    override fun showBalance(balance: User.Balance) {
        val balanceHomeData = balance.toBalanceHomeData()
        homeTxtBalance.text = balanceHomeData.quantity.toCurrency(balanceHomeData.symbol)
    }

    override fun showWallet(wallet: List<User.Balance>) {
        walletAdapter.data = wallet.map {
            it.toBalanceHomeData()
        }
    }

    override fun goToProfile() {
        startActivity(ProfileActivity(this))
    }

    override fun goToExchange() {
        startActivity(ExchangeActivity(this))
    }

    override fun goToHistory() {
        startActivity(HistoryActivity(this))
    }

    override fun goToAdd(currency: Currency) {
        startActivity(ExchangeActivity(this))
    }

    private fun setupViews() {
        homeRecWallet.layoutManager = LinearLayoutManager(this)
        homeRecWallet.adapter = walletAdapter

        homeMenuBottom.bmhBtnProfile.setSafeOnClickListener {
            presenter.onProfile()
        }

        homeMenuBottom.bmhBtnExchange.setSafeOnClickListener {
            presenter.onExchange()
        }

        homeMenuBottom.bmhBtnHistory.setSafeOnClickListener {
            presenter.onHistory()
        }
    }

    operator fun User.Balance.invoke(): String {
        return this.currencyType
    }

    private fun User.Balance.toBalanceHomeData(): BalanceHomeData {
        val currency = Currency(this.currencyType)
        val currencyResource = Library.getResource(currency)

        return BalanceHomeData(
            this.currencyType,
            this.quantity,
            currencyResource.name.str,
            currencyResource.icon,
            currencyResource.color,
            currencyResource.name.str,
            currencyResource.pluralName.str,
            currencyResource.symbol.str
        )
    }

    private val Int.str get() = getString(this)
}