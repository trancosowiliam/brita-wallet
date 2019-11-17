package br.com.britawallet.base.di

import br.com.britawallet.feature.login.LoginContract
import br.com.britawallet.feature.login.LoginPresenter
import org.koin.dsl.bind
import org.koin.dsl.module
import br.com.britawallet.feature.reward.RewardContract
import br.com.britawallet.feature.reward.RewardPresenter
import br.com.britawallet.feature.main.MainContract
import br.com.britawallet.feature.main.MainPresenter

val appModule = module {
    factory { (view: MainContract.View) ->
        MainPresenter(
            view = view
        )
    } bind MainContract.Presenter::class

    factory { (view: LoginContract.View) ->
        LoginPresenter(
            view = view,
            userRemoteRepository = get(),
            userLocalRepository = get(),
            analytics = get(),
            dictionary = get(),
            dispatcher = get()
        )
    } bind LoginContract.Presenter::class

    factory { (view: RewardContract.View) ->
        RewardPresenter(
            view = view
        )
    } bind RewardContract.Presenter::class
}
