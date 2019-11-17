package br.com.britawallet.base.di

import br.com.britawallet.data.remote.UserRemoteRepository
import com.nhaarman.mockitokotlin2.mock
import org.koin.dsl.module

val remoteRepositoryModule = module {
    factory {
        mock<UserRemoteRepository>()
    }
}