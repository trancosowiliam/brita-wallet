package br.com.britawallet.feature.history

import br.com.britawallet.data.local.StaticResources
import br.com.britawallet.data.model.Currency
import br.com.britawallet.data.model.getBodyOrNull
import br.com.britawallet.data.remote.TransactionRemoteRepository

class HistoryPresenter(
    override var view: HistoryContract.View,
    internal val staticResources: StaticResources,
    internal val transactionRemoteRepository: TransactionRemoteRepository
) : HistoryContract.Presenter {

    override fun loadBalance() {
        staticResources.user?.let { user ->
            val balance = user.wallet.first { balance ->
                Currency[balance.currencyType].isDefault
            }

            view.showBalance(balance)
        }
    }

    override fun loadHistory() {
        val user = staticResources.user ?: return
        val transactions = transactionRemoteRepository.getAll(user.login).getBodyOrNull() ?: return

        view.showHistory(transactions)
    }
}