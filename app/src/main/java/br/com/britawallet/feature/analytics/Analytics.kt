package br.com.britawallet.feature.analytics

interface Analytics {
    fun eventLoginSuccessful(login: String)
    fun eventLoginFailure(login: String, errorType: String)
    fun eventOnRegister()
    fun eventOnForgotPassword()

    fun onProfile()
    fun onExchange()
    fun onHistory()
}