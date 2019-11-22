package br.com.britawallet.data.remote

import br.com.britawallet.data.entity.TransactionEntity
import br.com.britawallet.data.local.dao.TransactionDao
import br.com.britawallet.data.model.ServiceResponse
import br.com.britawallet.data.model.Transaction
import br.com.britawallet.data.model.toServiceBody

class TransactionMockRemoteRepository(
    private val transactionDao: TransactionDao
) :
    TransactionRemoteRepository {
    override fun getAll(login: String): ServiceResponse<List<Transaction>> {
        return transactionDao.getAll(login).map { it.toTransaction() }.toServiceBody()
    }

    override fun save(transaction: Transaction) = ServiceResponse.OK.apply {
        transactionDao.save(transaction.toEntity())
    }

    private fun TransactionEntity.toTransaction() =
        Transaction(
            buyType = buyType,
            sellType = sellType,
            buy = buy,
            sell = sell,
            date = date,
            user = user
        )

    private fun Transaction.toEntity() =
        TransactionEntity(
            buyType = buyType,
            sellType = sellType,
            buy = buy,
            sell = sell,
            date = date,
            user = user
        )
}