package br.com.britawallet.feature.main

import br.com.britawallet.base.di.testModules
import org.junit.Before
import org.koin.core.context.startKoin
import org.koin.core.parameter.parametersOf
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject

class MainPresenterTest: AutoCloseKoinTest() {
    private val view: MainContract.View by inject()

    private val presenter by inject<MainPresenter>{
       parametersOf(view)
    }

    @Before
    fun before() {
        startKoin {
            modules(testModules)
        }
    }

}