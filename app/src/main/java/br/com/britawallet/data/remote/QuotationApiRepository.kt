package br.com.britawallet.data.remote

import br.com.britawallet.base.extensions.MMddyyyy
import br.com.britawallet.base.extensions.awaitResult
import br.com.britawallet.data.global.Dictionary
import br.com.britawallet.data.model.Currency
import br.com.britawallet.data.model.ServiceResponse
import br.com.britawallet.data.model.getBodyOrNull
import br.com.britawallet.data.model.toServiceBody
import br.com.britawallet.data.remote.api.BancoCentralApi
import br.com.britawallet.data.remote.api.MercadoBitcoinApi
import java.util.*

@Suppress("SpellCheckingInspection")
class QuotationApiRepository(
    private val mercadoBitcoinApi: MercadoBitcoinApi,
    private val bancoCentralApi: BancoCentralApi,
    private val dictionary: Dictionary
) : QuotationRepository {

    override suspend fun getQuotation(): ServiceResponse<List<Currency>> {
        val quotationBtc = mercadoBitcoinApi.getBitcoin()
            .awaitResult()
            .getBodyOrNull()
            ?.quotation()

        val quotationBtr = bancoCentralApi.getDolar("'${Date().MMddyyyy()}'")
            .awaitResult()
            .getBodyOrNull()
            ?.quotation()

        return when {
            quotationBtc == null -> ServiceResponse.ERROR(dictionary.SERVICE_ERROR_GET_QUOTATION)
            quotationBtr == null -> ServiceResponse.ERROR(dictionary.SERVICE_ERROR_GET_QUOTATION)
            else -> {
                Currency.load(quotationBtc, quotationBtr)
                Currency.all.toServiceBody()
            }
        }
    }
}