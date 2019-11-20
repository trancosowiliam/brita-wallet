package br.com.britawallet.core

import android.content.Context
import br.com.britawallet.R
import br.com.britawallet.data.global.Dictionary
import br.com.britawallet.data.model.Currency

class StringXmlDictionary(val context: Context) : Dictionary {
    override val LOGIN_EMPTY_USER: String
        get() = R.string.login_message_empty_user_login.str

    override val LOGIN_EMPTY_PASSWORD: String
        get() = R.string.login_message_empty_user_password.str

    override val SERVICE_ERROR_INVALID_LOGIN_OR_PASSWORD: String
        get() = R.string.login_message_invalid_login_or_password.str

    private val Int.str
        get() = context.getString(this)

    override fun getCurrencyName(currency: Currency): String {
        return context.getString(
            when (currency) {
                Currency.BRL -> R.string.todo_title
                else -> R.string.todo_title
            }
        )
    }
}