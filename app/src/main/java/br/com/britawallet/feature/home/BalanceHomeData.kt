package br.com.britawallet.feature.home

import java.math.BigDecimal

data class BalanceHomeData(
    val currencyType: String,
    val quantity: BigDecimal,
    val name: String,
    val icon: Int,
    val color: Int,
    val description: String,
    val pluralName: String,
    val symbol: String
)