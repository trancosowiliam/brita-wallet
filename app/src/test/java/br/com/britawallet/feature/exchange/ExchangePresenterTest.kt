package br.com.britawallet.feature.exchange

import br.com.britawallet.base.di.testModules
import org.junit.Before
import org.koin.core.context.startKoin
import org.koin.core.parameter.parametersOf
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject

class ExchangePresenterTest: AutoCloseKoinTest() {
    private val view: ExchangeContract.View by inject()

    private val presenter by inject<ExchangePresenter>{
       parametersOf(view)
    }

    @Before
    fun before() {
        startKoin {
            modules(testModules)
        }
    }

}