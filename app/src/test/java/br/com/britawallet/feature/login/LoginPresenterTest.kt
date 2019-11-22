package br.com.britawallet.feature.login

import br.com.britawallet.base.di.testModules
import br.com.britawallet.data.local.mockHasUser
import br.com.britawallet.data.local.mockNoHasUser
import br.com.britawallet.data.remote.LOGIN_FAILURE
import br.com.britawallet.data.remote.LOGIN_FIRST_LOGIN
import br.com.britawallet.data.remote.LOGIN_SUCCESSFUL
import br.com.britawallet.data.remote.VALID_PASSWORD
import br.com.britawallet.data.remote.initMock
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.parameter.parametersOf
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject
import org.mockito.ArgumentMatchers.anyString

class LoginPresenterTest : AutoCloseKoinTest() {
    private val view: LoginContract.View by inject()

    private val presenter by inject<LoginPresenter> {
        parametersOf(view)
    }

    @Before
    fun before() {
        startKoin {
            modules(testModules)
        }
    }

    @Test
    fun `test when has user app then go to home`() = runBlocking {
        presenter.userLocalRepository.mockHasUser()
        presenter.checkAppUser()

        verify(view).goToHome()
        verify(view).closeWindow()
        verify(presenter.staticResources).user = any()

        verifyNoMoreInteractions(view)
    }

    @Test
    fun `test when no has user app then wait`() = runBlocking {
        presenter.userLocalRepository.mockNoHasUser()
        presenter.checkAppUser()

        verify(view).setupViews()

        verifyNoMoreInteractions(view)
    }

    @Test
    fun `test when empty login then show empty login message`() = runBlocking {
        presenter.login("", VALID_PASSWORD)

        verify(view).showLoginFieldError(anyString())

        verifyNoMoreInteractions(
            view,
            presenter.userRemoteRepository,
            presenter.userLocalRepository,
            presenter.analytics
        )
    }

    @Test
    fun `test when empty password then show empty password message`() = runBlocking {
        presenter.login(LOGIN_SUCCESSFUL, "")

        verify(view).showPasswordFieldError(anyString())

        verifyNoMoreInteractions(
            view,
            presenter.userRemoteRepository,
            presenter.userLocalRepository,
            presenter.analytics
        )
    }

    @Test
    fun `test when first login successful then go reward`() = runBlocking {
        presenter.userRemoteRepository.initMock()
        presenter.login(LOGIN_FIRST_LOGIN, VALID_PASSWORD)

        verify(view).showLoginLoading()
        verify(presenter.analytics).eventLoginSuccessful(anyString())
        verify(presenter.userLocalRepository).login(any())
        verify(view).goToReward()
        verify(view).closeWindow()
        verify(presenter.staticResources).user = any()

        verifyNoMoreInteractions(
            view,
            presenter.analytics
        )
    }

    @Test
    fun `test when login successful then go home`() = runBlocking {
        presenter.userRemoteRepository.initMock()
        presenter.login(LOGIN_SUCCESSFUL, VALID_PASSWORD)

        verify(view).showLoginLoading()
        verify(presenter.analytics).eventLoginSuccessful(anyString())
        verify(presenter.userLocalRepository).login(any())
        verify(view).goToHome()
        verify(view).closeWindow()
        verify(presenter.staticResources).user = any()

        verifyNoMoreInteractions(
            view,
            presenter.analytics
        )
    }

    @Test
    fun `test when login failure then show message`() = runBlocking {
        presenter.userRemoteRepository.initMock()
        presenter.login(LOGIN_FAILURE, VALID_PASSWORD)

        verify(view).showLoginLoading()
        verify(presenter.analytics).eventLoginFailure(anyString(), anyString())
        verify(view).hideLoginLoading()
        verify(view).showLoginFailureMessage(anyString())

        verifyNoMoreInteractions(
            view,
            presenter.analytics
        )
    }

    @Test
    fun `test when click on register then go to register`() {
        presenter.register()

        verify(presenter.analytics).eventOnRegister()
        verify(view).goToRegister()

        verifyNoMoreInteractions(
            view,
            presenter.userRemoteRepository,
            presenter.userLocalRepository,
            presenter.analytics
        )
    }

    @Test
    fun `test when click on forgot password then show dialog`() {
        presenter.forgotPassword()

        verify(presenter.analytics).eventOnForgotPassword()
        verify(view).showForgotPasswordDialog()

        verifyNoMoreInteractions(
            view,
            presenter.userRemoteRepository,
            presenter.userLocalRepository,
            presenter.analytics
        )
    }
}