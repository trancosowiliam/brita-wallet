package br.com.britawallet.feature.history

import br.com.britawallet.base.di.testModules
import br.com.britawallet.data.local.mockResources
import br.com.britawallet.data.model.Currency
import br.com.britawallet.data.remote.initMock
import com.nhaarman.mockitokotlin2.argThat
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.parameter.parametersOf
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject
import org.mockito.ArgumentMatchers.anyList

class HistoryPresenterTest: AutoCloseKoinTest() {
    private val view: HistoryContract.View by inject()

    private val presenter by inject<HistoryPresenter>{
       parametersOf(view)
    }

    @Before
    fun before() {
        startKoin {
            modules(testModules)
        }
    }

    @Test
    fun `test when load balance then show balance`() {
        presenter.staticResources.mockResources()

        presenter.loadBalance()

        verify(view).showBalance(argThat {
            Currency[this.currencyType].isDefault
        })
    }

    @Test
    fun `test when load history then show history`() {
        presenter.transactionRemoteRepository.initMock()
        presenter.staticResources.mockResources()

        presenter.loadHistory()

        verify(view).showHistory(anyList())
    }

}