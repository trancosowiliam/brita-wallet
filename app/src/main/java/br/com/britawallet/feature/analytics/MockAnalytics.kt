package br.com.britawallet.feature.analytics

class MockAnalytics : Analytics {
    
    override fun eventLoginSuccessful(login: String) {}
    override fun eventLoginFailure(login: String, errorType: String) {}
    override fun eventOnRegister() {}
    override fun eventOnForgotPassword() {}
}