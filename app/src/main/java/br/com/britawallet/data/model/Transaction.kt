package br.com.britawallet.data.model

data class Transaction(
    val buyType: String,
    val sellType: String,
    val buy: Double,
    val sell: Double,
    val date: String,
    val user: String
)