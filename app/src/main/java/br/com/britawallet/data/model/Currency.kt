package br.com.britawallet.data.model

class Currency(
    val type: String,
    var ratio: Double = 0.0,
    val isDefault: Boolean = false
) {
    fun convert(quantity: Double, to: Currency): Double {
        return quantity / this.ratio * to.ratio
    }

    companion object {
        val BRL = Currency("BRL", 1.0, true)
        val BTC = Currency("BTC")
        val BRT = Currency("BRT")

        var isLoaded: Boolean = false
        val Default = BRL
        val all = listOf(BRL, BTC, BRT)

        operator fun get(type: String): Currency {
            return all.firstOrNull { it() == type } ?: throw IndexOutOfBoundsException()
        }

        fun load(BTCRatio: Double, BRTRatio: Double) {
            BTC.ratio = BTCRatio
            BRT.ratio = BRTRatio

            isLoaded = true
        }
    }

    operator fun invoke() = type
}