package br.com.britawallet.base.di

import androidx.room.Room
import br.com.britawallet.data.local.StaticResources
import br.com.britawallet.data.local.UserLocalRepository
import br.com.britawallet.data.local.UserMockLocalRepository
import br.com.britawallet.data.local.dao.AppDatabase
import org.koin.dsl.bind
import org.koin.dsl.module

val localRepositoryModule = module {
    factory {
        UserMockLocalRepository(
            userDao = get(),
            balanceDao = get()
        )
    } bind UserLocalRepository::class

    single { StaticResources() }
}

@Suppress("USELESS_CAST")
val roomDatabaseModule = module {
    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, "database")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    } bind AppDatabase::class

    factory {
        get<AppDatabase>().userDao()
    }

    factory {
        get<AppDatabase>().balanceDao()
    }
}