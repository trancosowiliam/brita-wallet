package br.com.britawallet.feature.login

import br.com.britawallet.base.BasePresenter
import br.com.britawallet.base.BaseView

interface LoginContract {
    interface View : BaseView<Presenter> {
        fun showLoginLoading()
        fun hideLoginLoading()

        fun showLoginFailureMessage(message: String)
        fun showLoginFieldError(message: String)
        fun showPasswordFieldError(message: String)

        fun goToReward()
        fun goToMain()
        fun closeWindow()

        fun goToRegister()

        fun showForgotPasswordDialog()
    }

    interface Presenter : BasePresenter<View> {
        fun checkAppUser()
        fun login(login: String, password: String)
        fun register()
        fun forgotPassword()
    }
}