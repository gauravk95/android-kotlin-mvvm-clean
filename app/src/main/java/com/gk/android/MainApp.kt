package com.gk.android

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.gk.android.data.injection.createRemoteModule
import com.gk.android.domain.injection.domainModule
import com.gk.android.artist.injection.artistModule
import com.gk.android.artistinfo.injection.artistInfoModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level
import org.koin.core.context.startKoin

open class MainApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MainApp)

            modules(
                listOf(
                    domainModule,
                    createRemoteModule(BuildConfig.API_ENDPOINT),
                    artistModule,
                    artistInfoModule
                )
            )
        }

        AndroidThreeTen.init(this)
    }
}