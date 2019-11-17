package br.com.britawallet.data.model

import java.math.BigDecimal

data class User(
    val login: String,
    val name: String,
    val wallet: List<Balance>,
    val isFirstLogin: Boolean
) {
    data class Balance(
        val currencyType: String,
        val quantity: BigDecimal
    )
}