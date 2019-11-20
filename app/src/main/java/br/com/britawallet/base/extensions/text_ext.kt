package br.com.britawallet.base.extensions

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

fun Number.toCurrency(currencySymbol: String = ""): String {
    val nf = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
    val decimalFormatSymbols = (nf as DecimalFormat).decimalFormatSymbols
    decimalFormatSymbols.currencySymbol = currencySymbol
    nf.decimalFormatSymbols = decimalFormatSymbols

    return nf.format(this)
}