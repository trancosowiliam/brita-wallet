package br.com.britawallet.feature.profile

import br.com.britawallet.base.di.testModules
import br.com.britawallet.data.local.mockResources
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.parameter.parametersOf
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject
import org.mockito.ArgumentMatchers.anyString

class ProfilePresenterTest : AutoCloseKoinTest() {
    private val view: ProfileContract.View by inject()

    private val presenter by inject<ProfilePresenter> {
        parametersOf(view)
    }

    @Before
    fun before() {
        startKoin {
            modules(testModules)
        }
    }

    @Test
    fun `test when load user then show user`() {
        presenter.staticResources.mockResources()
        presenter.loadUser()

        verify(view).showUserData(any())

        verifyNoMoreInteractions(view, presenter.analytics)
    }

    @Test
    fun `test when on logout then log analytics and logout`() {
        presenter.onLogout("login")

        verify(presenter.userLocalRepository).logout()
        verify(presenter.analytics).onLogout(anyString())
        verify(presenter.staticResources).user = null
        verify(view).close()

        verifyNoMoreInteractions(
            view,
            presenter.userLocalRepository,
            presenter.analytics,
            presenter.staticResources
        )
    }

    @Test
    fun `test when on delete account then log analytics and show dialog`() {
        presenter.staticResources.mockResources()
        presenter.onDeleteAccount("login")

        verify(presenter.analytics).onDeleteAccount(anyString())
        verify(view).showDeleteAccountDialog()
    }

    @Test
    fun `test when on edit user then log analytics and go to screen`() {
        presenter.staticResources.mockResources()
        presenter.onEditUser("login")

        verify(presenter.analytics).onEditUser(anyString())
        verify(view).goToEditUser(any())
    }
}