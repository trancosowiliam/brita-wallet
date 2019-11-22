package br.com.britawallet.base.di

import br.com.britawallet.data.remote.QuotationApiRepository
import br.com.britawallet.data.remote.QuotationRepository
import br.com.britawallet.data.remote.TransactionMockRemoteRepository
import br.com.britawallet.data.remote.TransactionRemoteRepository
import br.com.britawallet.data.remote.UserMockRemoteRepository
import br.com.britawallet.data.remote.UserRemoteRepository
import br.com.britawallet.data.remote.api.BancoCentralApi
import br.com.britawallet.data.remote.api.MercadoBitcoinApi
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit

val remoteRepositoryModule = module {

    factory { get<Retrofit>(BANCO_CENTRAL.toQualifier()).create(BancoCentralApi::class.java) }
    factory { get<Retrofit>(MERCADO_BITCOIN.toQualifier()).create(MercadoBitcoinApi::class.java) }

    factory {

        UserMockRemoteRepository(
            dictionary = get(),
            userLocalRepository = get()
        )
    } bind UserRemoteRepository::class

    factory {
        TransactionMockRemoteRepository(
            transactionDao = get()
        )
    } bind TransactionRemoteRepository::class

    factory {
        QuotationApiRepository(
            mercadoBitcoinApi = get(),
            bancoCentralApi = get(),
            dictionary = get()
        )
    } bind QuotationRepository::class
}