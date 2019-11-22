package br.com.britawallet.data.model

data class User(
    val login: String,
    val name: String,
    val wallet: List<Balance>,
    val isFirstLogin: Boolean
) {
    data class Balance(
        val currencyType: String,
        var quantity: Double
    )
}