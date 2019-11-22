package br.com.britawallet.feature.analytics

interface Analytics {
    fun eventLoginSuccessful(login: String)
    fun eventLoginFailure(login: String, errorType: String)
    fun eventOnRegister()
    fun eventOnForgotPassword()

    fun onLogout(login: String)
    fun onEditUser(login: String)
    fun onDeleteAccount(login: String)

    fun onProfile()
    fun onExchange()
    fun onHistory()
}