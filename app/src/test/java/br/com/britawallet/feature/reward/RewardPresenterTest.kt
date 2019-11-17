package br.com.britawallet.feature.reward

import br.com.britawallet.base.di.testModules
import org.junit.Before
import org.koin.core.context.startKoin
import org.koin.core.parameter.parametersOf
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject

class RewardPresenterTest: AutoCloseKoinTest() {
    private val view: RewardContract.View by inject()

    private val presenter by inject<RewardPresenter>{
       parametersOf(view)
    }

    @Before
    fun before() {
        startKoin {
            modules(testModules)
        }
    }

}