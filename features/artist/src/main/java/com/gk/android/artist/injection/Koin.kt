package com.gk.android.artist.injection

import com.gk.android.artist.ArtistViewModel
import com.gk.android.artist.BookmarkArtistViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val artistModule = module {
    viewModel { ArtistViewModel(get(), get()) }
    viewModel { BookmarkArtistViewModel(get(), get()) }
}