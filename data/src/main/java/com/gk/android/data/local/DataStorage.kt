package com.gk.android.data.local

import com.gk.android.data.artist.source.BookmarkArtistDao
import com.gk.android.data.artist.model.ArtistEntity
import com.gk.android.data.artist.toArtistEntity
import com.gk.android.domain.artist.model.Artist
import kotlinx.coroutines.flow.Flow

class DataStorage(
    private val bookmarkArtistDao: BookmarkArtistDao
) {
    suspend fun bookmarkArtist(artist: Artist) {
        bookmarkArtistDao.bookmarkArtist(artist.toArtistEntity())
    }

    suspend fun removeBookmarkedArtist(artist: Artist) {
        bookmarkArtistDao.removeBookmarkedArtist(artist.toArtistEntity())
    }

    fun getArtists(): Flow<List<ArtistEntity>> = bookmarkArtistDao.getArtists()
}