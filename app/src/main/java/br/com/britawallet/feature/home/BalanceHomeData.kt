package br.com.britawallet.feature.home

import android.content.Context
import br.com.britawallet.core.Library
import br.com.britawallet.data.model.Currency
import br.com.britawallet.data.model.User

data class BalanceHomeData(
    val currencyType: String,
    val quantity: Double,
    val name: String,
    val icon: Int,
    val button: Int,
    val color: Int,
    val description: String,
    val pluralName: String,
    val symbol: String
)

fun User.Balance.toBalanceHomeData(context: Context): BalanceHomeData {
    val currency = Currency[this.currencyType]
    val currencyResource = Library.getResource(currency)

    return BalanceHomeData(
        this.currencyType,
        this.quantity,
        context.getString(currencyResource.name),
        currencyResource.icon,
        currencyResource.button,
        currencyResource.color,
        context.getString(currencyResource.name),
        context.getString(currencyResource.pluralName),
        context.getString(currencyResource.symbol)
    )
}