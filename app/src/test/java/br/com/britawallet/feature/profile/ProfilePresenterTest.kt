package br.com.britawallet.feature.profile

import br.com.britawallet.base.di.testModules
import org.junit.Before
import org.koin.core.context.startKoin
import org.koin.core.parameter.parametersOf
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject

class ProfilePresenterTest: AutoCloseKoinTest() {
    private val view: ProfileContract.View by inject()

    private val presenter by inject<ProfilePresenter>{
       parametersOf(view)
    }

    @Before
    fun before() {
        startKoin {
            modules(testModules)
        }
    }

}