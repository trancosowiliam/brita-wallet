package br.com.britawallet.feature.history

data class TransactionHistoryData(
    val sellIcon: Int,
    val buyIcon: Int,
    val description: String,
    val date: String,
    val sellValueDescription: String,
    val buyValueDescription: String
)