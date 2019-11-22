package br.com.britawallet.feature.home

import br.com.britawallet.base.BasePresenter
import br.com.britawallet.base.BaseView
import br.com.britawallet.data.model.Currency
import br.com.britawallet.data.model.User

interface HomeContract {
    interface View : BaseView<Presenter> {
        fun showWallet(wallet: List<User.Balance>)
        fun showBalance(balance: User.Balance)

        fun goToProfile()
        fun goToExchange()
        fun goToHistory()

        fun goToAdd(currency: Currency)
    }

    interface Presenter : BasePresenter<View> {
        fun loadWallet()
        fun onProfile()
        fun onExchange()
        fun onHistory()
        fun onAdd(currency: Currency)
    }
}