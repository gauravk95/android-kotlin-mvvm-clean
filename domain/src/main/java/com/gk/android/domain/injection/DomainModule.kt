package com.gk.android.domain.injection

import com.gk.android.domain.artist.interactor.BookmarkArtist
import com.gk.android.domain.artist.interactor.GetArtistDetails
import com.gk.android.domain.artist.interactor.GetBookmarkedArtists
import com.gk.android.domain.artist.interactor.RemoveBookmarkedArtist
import com.gk.android.domain.artist.interactor.SearchArtist
import org.koin.dsl.module

val domainModule = module {
    factory { SearchArtist(get()) }
    factory { GetBookmarkedArtists(get()) }
    factory { BookmarkArtist(get()) }
    factory { RemoveBookmarkedArtist(get()) }
    factory { GetArtistDetails(get()) }
}