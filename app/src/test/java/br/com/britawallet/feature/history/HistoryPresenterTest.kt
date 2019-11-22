package br.com.britawallet.feature.history

import br.com.britawallet.base.di.testModules
import org.junit.Before
import org.koin.core.context.startKoin
import org.koin.core.parameter.parametersOf
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject

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

}