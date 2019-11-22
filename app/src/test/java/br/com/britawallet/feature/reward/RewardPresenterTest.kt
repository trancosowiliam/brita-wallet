package br.com.britawallet.feature.reward

import br.com.britawallet.base.di.testModules
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.parameter.parametersOf
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject

class RewardPresenterTest : AutoCloseKoinTest() {
    private val view: RewardContract.View by inject()

    private val presenter by inject<RewardPresenter> {
        parametersOf(view)
    }

    @Before
    fun before() {
        startKoin {
            modules(testModules)
        }
    }

    @Test
    fun `test when click on start then open home and close reward`() {
        presenter.begin()

        verify(view).goToHome()
        verify(view).closeWindow()
    }
}