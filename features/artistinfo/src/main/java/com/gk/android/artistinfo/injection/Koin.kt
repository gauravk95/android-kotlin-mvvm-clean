package com.gk.android.artistinfo.injection

import com.gk.android.artistinfo.ArtistDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val artistInfoModule = module {
    viewModel { ArtistDetailsViewModel(get(), get()) }
}