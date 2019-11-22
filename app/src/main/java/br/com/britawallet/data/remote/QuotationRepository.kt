package br.com.britawallet.data.remote

import br.com.britawallet.data.model.Currency
import br.com.britawallet.data.model.ServiceResponse

interface QuotationRepository {
    suspend fun getQuotation(): ServiceResponse<List<Currency>>
}