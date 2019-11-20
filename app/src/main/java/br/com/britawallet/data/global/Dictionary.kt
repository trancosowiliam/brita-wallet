package br.com.britawallet.data.global

import br.com.britawallet.data.model.Currency

@Suppress("PropertyName")
interface Dictionary {
    val LOGIN_EMPTY_USER: String
    val LOGIN_EMPTY_PASSWORD: String

    val SERVICE_ERROR_INVALID_LOGIN_OR_PASSWORD: String

    fun getCurrencyName(currency: Currency): String
}