package br.com.britawallet.feature.home

import br.com.britawallet.data.local.StaticResources
import br.com.britawallet.data.model.Currency
import br.com.britawallet.feature.analytics.Analytics

class HomePresenter(
    override var view: HomeContract.View,
    internal val staticResources: StaticResources,
    internal val analytics: Analytics
) : HomeContract.Presenter {

    override fun loadWallet() {
        staticResources.user?.let { user ->
            val balance = user.wallet.first { balance ->
                Currency(balance.currencyType).isDefault
            }

            val wallet = user.wallet.filter { balance ->
                !Currency(balance.currencyType).isDefault
            }

            view.showBalance(balance)
            view.showWallet(wallet)
        }
    }

    override fun onProfile() {
        analytics.onProfile()
        view.goToProfile()
    }

    override fun onExchange() {
        analytics.onExchange()
        view.goToExchange()
    }

    override fun onHistory() {
        analytics.onHistory()
        view.goToHistory()
    }

    override fun onAdd(currency: Currency) {
        analytics.onExchange()
        view.goToAdd(currency)
    }
}