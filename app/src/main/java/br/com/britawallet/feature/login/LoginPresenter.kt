package br.com.britawallet.feature.login

import br.com.britawallet.base.extensions.launch
import br.com.britawallet.data.local.UserLocalRepository
import br.com.britawallet.data.model.User
import br.com.britawallet.data.model.whenever
import br.com.britawallet.data.model.withBody
import br.com.britawallet.data.remote.UserRemoteRepository
import br.com.britawallet.feature.analytics.Analytics
import br.com.britawallet.data.global.Dictionary
import kotlinx.coroutines.CoroutineDispatcher

class LoginPresenter(
    override var view: LoginContract.View,
    internal val userRemoteRepository: UserRemoteRepository,
    internal val userLocalRepository: UserLocalRepository,
    internal val analytics: Analytics,
    private val dictionary: Dictionary,
    private val dispatcher: CoroutineDispatcher
) : LoginContract.Presenter {

    override fun checkAppUser() {
        dispatcher.launch {
            userLocalRepository.getActiveUser().withBody {
                view.goToMain()
                view.closeWindow()
            }
        }
    }

    override fun login(login: String, password: String) {
        if (login.isEmpty()) {
            view.showLoginFieldError(dictionary.LOGIN_EMPTY_USER)
            return
        }

        if (password.isEmpty()) {
            view.showPasswordFieldError(dictionary.LOGIN_EMPTY_PASSWORD)
            return
        }

        dispatcher.launch {
            view.showLoginLoading()
            userRemoteRepository.login(login, password).whenever(
                isBody = { user ->
                    analytics.eventLoginSuccessful(login)
                    onLoginSuccessful(user)
                },
                isError = { message ->
                    analytics.eventLoginFailure(login, message)
                    view.hideLoginLoading()
                    view.showLoginFailureMessage(message)
                }
            )
        }
    }

    override fun register() {
        analytics.eventOnRegister()
        view.goToRegister()
    }

    override fun forgotPassword() {
        analytics.eventOnForgotPassword()
        view.showForgotPasswordDialog()
    }

    private fun onLoginSuccessful(user: User) {
        userLocalRepository.login(user)

        if (user.isFirstLogin) {
            isFirstLogin()
        } else {
            onNormalLogin()
        }
    }

    private fun isFirstLogin() {
        view.goToReward()
        view.closeWindow()
    }

    private fun onNormalLogin() {
        view.goToMain()
        view.closeWindow()
    }
}