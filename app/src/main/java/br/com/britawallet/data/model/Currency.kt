package br.com.britawallet.data.model

sealed class Currency(
    private val type: String,
    var ratio: Double? = null,
    val isDefault: Boolean = false
) {
    object BRL : Currency("BRL", 1.0, true)
    object BTC : Currency("BTC")
    object BRT : Currency("BRT")

    companion object {
        val Default = BRL

        val all = listOf(
            BRL,
            BTC,
            BRT
        )

        operator fun invoke(type: String): Currency {
            return all.firstOrNull { it() == type } ?: throw IndexOutOfBoundsException()
        }
    }

    operator fun invoke() = type
}