package br.com.britawallet.base.di

import br.com.britawallet.feature.login.LoginContract
import br.com.britawallet.feature.reward.RewardContract
import com.nhaarman.mockitokotlin2.mock
import org.koin.dsl.module

val viewModule = module {
    factory { mock<LoginContract.View>() }
    factory { mock<RewardContract.View>() }
}