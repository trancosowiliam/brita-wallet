package br.com.britawallet.data.remote.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

@Suppress("SpellCheckingInspection")
interface BancoCentralApi {
    @GET("CotacaoDolarDia(dataCotacao=@dataCotacao)?format=json")
    fun getDolar(
        @Query("@dataCotacao", encoded = true)
        dataCotacao: String
    ): Call<BancoCentralQuotation>
}

@Suppress("SpellCheckingInspection")
data class BancoCentralQuotation(
    val value: List<Value>
) {
    fun quotation(): Double? {
        return  value.getOrNull(0)?.cotacaoCompra
    }
}

@Suppress("SpellCheckingInspection")
data class Value(
    val cotacaoCompra: Double
)
