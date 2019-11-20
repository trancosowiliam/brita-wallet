package br.com.britawallet.core

import br.com.britawallet.R
import br.com.britawallet.data.model.Currency

sealed class CurrencyResource(
    val icon: Int,
    val color: Int,
    val name: Int,
    val pluralName: Int,
    val symbol: Int
) {
    object BRL : CurrencyResource(
        icon = R.drawable.ic_currency_brl,
        color = R.color.color_currency_brl,
        name = R.string.currency_brl_name,
        pluralName = R.string.currency_brl_plural_name,
        symbol = R.string.currency_brl_symbol
    )

    object BTC : CurrencyResource(
        icon = R.drawable.ic_currency_btc,
        color = R.color.color_currency_btc,
        name = R.string.currency_btc_name,
        pluralName = R.string.currency_btc_plural_name,
        symbol = R.string.currency_btc_symbol
    )

    object BRT : CurrencyResource(
        icon = R.drawable.ic_currency_brt,
        color = R.color.color_currency_brt,
        name = R.string.currency_brt_name,
        pluralName = R.string.currency_brt_plural_name,
        symbol = R.string.currency_brt_symbol
    )
}

class Library {
    companion object {
        fun getResource(currency: Currency): CurrencyResource {
            return when (currency) {
                Currency.BRL -> CurrencyResource.BRL
                Currency.BTC -> CurrencyResource.BTC
                Currency.BRT -> CurrencyResource.BRT
            }
        }
    }
}