package br.com.britawallet.feature.analytics

class MockAnalytics : Analytics {

    override fun eventLoginSuccessful(login: String) {}
    override fun eventLoginFailure(login: String, errorType: String) {}
    override fun eventOnRegister() {}
    override fun eventOnForgotPassword() {}
    override fun onProfile() {}
    override fun onExchange() {}
    override fun onHistory() {}
}