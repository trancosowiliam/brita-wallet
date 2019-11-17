package br.com.britawallet.base.di

import br.com.britawallet.data.local.UserLocalRepository
import com.nhaarman.mockitokotlin2.mock
import org.koin.dsl.module

val localRepositoryModule = module {
    factory {
        mock<UserLocalRepository>()
    }
}