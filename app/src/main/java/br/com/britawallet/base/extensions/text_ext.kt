package br.com.britawallet.base.extensions

import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

fun Number.toCurrency(currencySymbol: String = ""): String {
    val nf = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
    val decimalFormatSymbols = (nf as DecimalFormat).decimalFormatSymbols
    decimalFormatSymbols.currencySymbol = currencySymbol
    nf.decimalFormatSymbols = decimalFormatSymbols

    return nf.format(this).trim()
}

@Suppress("SpellCheckingInspection")
fun Date.ddMMyyyy(): String {
    return SimpleDateFormat("dd/MM/yyyy").apply {
        timeZone = TimeZone.getTimeZone("GMT-3")
    }.format(this)
}

@Suppress("FunctionName", "SpellCheckingInspection")
fun Date.MMddyyyy(): String {
    return SimpleDateFormat("MM/dd/yyyy").apply {
        timeZone = TimeZone.getTimeZone("GMT-3")
    }.format(this)
}