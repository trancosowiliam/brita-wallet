package br.com.britawallet.feature.home

import br.com.britawallet.base.di.testModules
import org.junit.Before
import org.koin.core.context.startKoin
import org.koin.core.parameter.parametersOf
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject

class HomePresenterTest: AutoCloseKoinTest() {
    private val view: HomeContract.View by inject()

    private val presenter by inject<HomePresenter>{
       parametersOf(view)
    }

    @Before
    fun before() {
        startKoin {
            modules(testModules)
        }
    }

}