package br.com.britawallet.base.di

import br.com.britawallet.feature.exchange.ExchangeContract
import br.com.britawallet.feature.history.HistoryContract
import br.com.britawallet.feature.home.HomeContract
import br.com.britawallet.feature.login.LoginContract
import br.com.britawallet.feature.profile.ProfileContract
import br.com.britawallet.feature.reward.RewardContract
import com.nhaarman.mockitokotlin2.mock
import org.koin.dsl.module

val viewModule = module {
    factory { mock<HomeContract.View>() }
    factory { mock<LoginContract.View>() }
    factory { mock<RewardContract.View>() }
    factory { mock<ExchangeContract.View>() }
    factory { mock<ProfileContract.View>() }
    factory { mock<HistoryContract.View>() }
}