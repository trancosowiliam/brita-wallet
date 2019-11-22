package br.com.britawallet.base.di

import br.com.britawallet.feature.home.HomeContract
import br.com.britawallet.feature.home.HomePresenter
import br.com.britawallet.feature.login.LoginContract
import br.com.britawallet.feature.login.LoginPresenter
import br.com.britawallet.feature.reward.RewardContract
import br.com.britawallet.feature.reward.RewardPresenter
import org.koin.dsl.bind
import org.koin.dsl.module
import br.com.britawallet.feature.profile.ProfileContract
import br.com.britawallet.feature.profile.ProfilePresenter
import br.com.britawallet.feature.exchange.ExchangeContract
import br.com.britawallet.feature.exchange.ExchangePresenter
import br.com.britawallet.feature.history.HistoryContract
import br.com.britawallet.feature.history.HistoryPresenter

val appModule = module {
    factory { (view: HomeContract.View) ->
        HomePresenter(
            view = view,
            staticResources = get(),
            analytics = get()
        )
    } bind HomeContract.Presenter::class

    factory { (view: LoginContract.View) ->
        LoginPresenter(
            view = view,
            userRemoteRepository = get(),
            userLocalRepository = get(),
            staticResources = get(),
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

    factory { (view: ProfileContract.View) ->
        ProfilePresenter(
            view = view
        )
    } bind ProfileContract.Presenter::class

    factory { (view: ExchangeContract.View) ->
        ExchangePresenter(
            view = view,
            staticResources = get(),
            quotationRepository = get(),
            userRemoteRepository = get(),
            transactionRemoteRepository = get(),
            dispatcher = get()
        )
    } bind ExchangeContract.Presenter::class

    factory { (view: HistoryContract.View) ->
        HistoryPresenter(
            view = view
        )
    } bind HistoryContract.Presenter::class
}
