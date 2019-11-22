package br.com.britawallet.data.remote.api

import retrofit2.Call
import retrofit2.http.GET

interface MercadoBitcoinApi {
    @GET("ticker")
    fun getBitcoin(): Call<MercadoBitcoinQuotation>
}

@Suppress("SpellCheckingInspection")
data class MercadoBitcoinQuotation(
    val ticker: Ticker
) {
    fun quotation(): Double? {
        return  ticker.open
    }
}

@Suppress("SpellCheckingInspection")
data class Ticker(
    val open: Double
)