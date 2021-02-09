package com.gk.android.data.injection

import androidx.room.Room
import com.apollographql.apollo.ApolloClient
import com.gk.android.data.BuildConfig
import com.gk.android.data.artist.repo.ArtistRepositoryImpl
import com.gk.android.data.remote.RemoteService
import com.gk.android.data.local.DataStorage
import com.gk.android.data.local.LocalDatabase
import com.gk.android.domain.artist.repository.ArtistRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module
import java.util.concurrent.TimeUnit

fun createRemoteModule(baseUrl: String) = module {
    single { Room.databaseBuilder(androidApplication(), LocalDatabase::class.java, "local-db").build() }
    single { DataStorage(get<LocalDatabase>().bookmarkArtistDao()) }

    factory<OkHttpClient>(named("OkHttp:Plain")) {
        if (BuildConfig.DEBUG)
            getOkhttpClientBuilder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
                .build()
        else
            getOkhttpClientBuilder().build()
    }

    single<ApolloClient> {
        ApolloClient.builder()
            .serverUrl(baseUrl)
            .okHttpClient(get(named("OkHttp:Plain")))
            .build()
    }

    single { RemoteService(get()) }

    factory<ArtistRepository> { ArtistRepositoryImpl(get(), get()) }
}

fun getOkhttpClientBuilder(): OkHttpClient.Builder {
    return OkHttpClient.Builder()
        .callTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
}
