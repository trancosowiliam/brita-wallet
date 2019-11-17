package br.com.britawallet.data.model

sealed class Currency(
    private val type: String,
    var ratio: Double? = null
) {
    object BRL : Currency("BRL", 1.0)
    object USD : Currency("USD")
    object BRT : Currency("BRT")

    companion object {
        val all = listOf(
            BRL,
            USD,
            BRT
        )
    }

    operator fun invoke() = type
}