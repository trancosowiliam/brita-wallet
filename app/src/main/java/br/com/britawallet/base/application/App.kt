package br.com.britawallet.base.application

import android.app.Application
import br.com.britawallet.base.di.appModule
import br.com.britawallet.base.di.localRepositoryModule
import br.com.britawallet.base.di.remoteRepositoryModule
import br.com.britawallet.base.di.roomDatabaseModule
import br.com.britawallet.base.di.serviceModule
import br.com.britawallet.base.di.utilModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@Suppress("unused")
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)

            modules(
                listOf(
                    appModule,
                    serviceModule,
                    remoteRepositoryModule,
                    localRepositoryModule,
                    roomDatabaseModule,
                    utilModule
                )
            )
        }
    }
}
