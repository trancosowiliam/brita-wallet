package br.com.britawallet.data.remote

import br.com.britawallet.data.model.ServiceResponse
import br.com.britawallet.data.model.Transaction

interface TransactionRemoteRepository {
    fun getAll(login: String): ServiceResponse<List<Transaction>>
    fun save(transaction: Transaction): ServiceResponse<Nothing>
}