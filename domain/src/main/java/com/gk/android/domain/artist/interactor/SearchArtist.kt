package com.gk.android.domain.artist.interactor

import androidx.paging.PagingData
import com.gk.android.domain.artist.model.Artist
import com.gk.android.domain.artist.repository.ArtistRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class SearchArtist(
    private val artistRepository: ArtistRepository
) {
    suspend fun execute(searchQuery: String, pageSize: Int): Flow<PagingData<Artist>> =
        withContext(Dispatchers.IO) {
            artistRepository.searchArtists(searchQuery, pageSize)
        }
}