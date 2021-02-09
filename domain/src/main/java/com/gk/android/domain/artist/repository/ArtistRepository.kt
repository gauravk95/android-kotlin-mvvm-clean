package com.gk.android.domain.artist.repository

import androidx.paging.PagingData
import com.gk.android.domain.Resource
import com.gk.android.domain.artist.model.Artist
import kotlinx.coroutines.flow.Flow

interface ArtistRepository {
    suspend fun searchArtists(searchQuery: String, first: Int): Flow<PagingData<Artist>>
    suspend fun getArtistDetails(id: String): Resource<Artist?>
    suspend fun bookmarkArtist(artist: Artist)
    suspend fun removeBookmarkedArtist(artist: Artist)
    fun getBookmarkedArtists(): Flow<List<Artist>>
}