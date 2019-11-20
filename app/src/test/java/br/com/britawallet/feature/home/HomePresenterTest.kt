package br.com.britawallet.feature.home

import br.com.britawallet.base.di.testModules
import br.com.britawallet.data.local.mockResources
import br.com.britawallet.data.model.Currency
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.argThat
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.parameter.parametersOf
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject

class HomePresenterTest : AutoCloseKoinTest() {
    private val view: HomeContract.View by inject()

    private val presenter by inject<HomePresenter> {
        parametersOf(view)
    }

    @Before
    fun before() {
        startKoin {
            modules(testModules)
        }
    }

    @Test
    fun `when load wallet then show wallet`() {
        presenter.staticResources.mockResources()
        presenter.loadWallet()

        // all except default balance
        verify(view).showWallet(argThat {
            this.size == Currency.all.filter { !it.isDefault }.size
        })
    }

    @Test
    fun `when load wallet then show balance`() {
        presenter.staticResources.mockResources()
        presenter.loadWallet()

        verify(view).showBalance(argThat {
            Currency(this.currencyType).isDefault
        })
    }

    @Test
    fun `when click on profile then log analytics and go to profile`() {
        presenter.onProfile()

        verify(presenter.analytics).onProfile()
        verify(view).goToProfile()

        verifyNoMoreInteractions(
            view,
            presenter.analytics
        )
    }

    @Test
    fun `when click on exchange then log analytics and go to exchange`() {
        presenter.onExchange()

        verify(presenter.analytics).onExchange()
        verify(view).goToExchange()

        verifyNoMoreInteractions(
            view,
            presenter.analytics
        )
    }

    @Test
    fun `when click on history then log analytics and go to history`() {
        presenter.onHistory()

        verify(presenter.analytics).onHistory()
        verify(view).goToHistory()

        verifyNoMoreInteractions(
            view,
            presenter.analytics
        )
    }

    @Test
    fun `when click on add value then log analytics and go to exchange`() {
        presenter.onAdd(mock())

        verify(presenter.analytics).onExchange()
        verify(view).goToAdd(any())

        verifyNoMoreInteractions(
            view,
            presenter.analytics
        )
    }
}