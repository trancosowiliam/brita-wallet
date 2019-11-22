package br.com.britawallet.data.remote

import br.com.britawallet.data.model.Currency
import br.com.britawallet.data.model.toServiceBody
import com.nhaarman.mockitokotlin2.whenever

suspend fun QuotationRepository.initMock() {
    whenever(this.getQuotation()).thenReturn(
        Currency.all.apply {
            forEachIndexed { index, currency ->
                currency.ratio = index.toDouble()
            }
        }.toServiceBody()
    )
}