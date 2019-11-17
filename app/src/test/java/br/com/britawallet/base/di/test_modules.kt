package br.com.britawallet.base.di

import br.com.britawallet.feature.analytics.Analytics
import br.com.britawallet.data.global.Dictionary
import br.com.britawallet.feature.dictionary.MockDictionary
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers.Unconfined
import org.koin.dsl.bind
import org.koin.dsl.module

val testUtilModule = module {
    factory { Unconfined } bind CoroutineDispatcher::class

    factory { mock<Analytics>() }

    factory { MockDictionary() } bind Dictionary::class
}

val testModules = listOf(
    viewModule,
    appModule,
    testUtilModule,
    localRepositoryModule,
    remoteRepositoryModule
)