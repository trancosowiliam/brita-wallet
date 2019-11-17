package br.com.britawallet.base.di

import br.com.britawallet.feature.analytics.Analytics
import br.com.britawallet.feature.analytics.MockAnalytics
import br.com.britawallet.data.global.Dictionary
import br.com.britawallet.data.global.StringXmlDictionary
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.bind
import org.koin.dsl.module

val utilModule = module {
    factory { Dispatchers.Main } bind CoroutineDispatcher::class

    factory { MockAnalytics() } bind Analytics::class

    factory {
        StringXmlDictionary(
            context = get()
        )
    } bind Dictionary::class
}
