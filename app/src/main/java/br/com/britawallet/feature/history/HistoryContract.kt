package br.com.britawallet.feature.history

import br.com.britawallet.base.BasePresenter
import br.com.britawallet.base.BaseView
import br.com.britawallet.data.model.Transaction
import br.com.britawallet.data.model.User

interface HistoryContract {
    interface View : BaseView<Presenter> {
        fun showBalance(balance: User.Balance)
        fun showHistory(transactions: List<Transaction>)
    }

    interface Presenter : BasePresenter<View> {
        fun loadBalance()
        fun loadHistory()
    }
}