package br.com.britawallet.core

import android.content.Context
import br.com.britawallet.R
import br.com.britawallet.data.global.Dictionary

class StringXmlDictionary(val context: Context) : Dictionary {
    override val LOGIN_EMPTY_USER: String
        get() = R.string.login_message_empty_user_login.str

    override val LOGIN_EMPTY_PASSWORD: String
        get() = R.string.login_message_empty_user_password.str

    override val SERVICE_ERROR_INVALID_LOGIN_OR_PASSWORD: String
        get() = R.string.login_message_invalid_login_or_password.str

    override val SERVICE_ERROR_GET_QUOTATION: String
        get() = R.string.exchange_fatal_error.str

    private val Int.str
        get() = context.getString(this)
}