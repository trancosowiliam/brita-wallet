package br.com.britawallet.base.di

import br.com.britawallet.data.remote.UserMockRemoteRepository
import br.com.britawallet.data.remote.UserRemoteRepository
import org.koin.dsl.bind
import org.koin.dsl.module

val remoteRepositoryModule = module {
    factory {
        UserMockRemoteRepository(
            dictionary = get(),
            userLocalRepository = get()
        )
    } bind UserRemoteRepository::class
}