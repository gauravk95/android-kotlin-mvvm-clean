package com.gk.android.domain.artist.interactor

import com.gk.android.domain.artist.model.Artist
import com.gk.android.domain.artist.repository.ArtistRepository
import kotlinx.coroutines.flow.Flow

class GetBookmarkedArtists(
    private val artistRepository: ArtistRepository
) {
    fun execute(): Flow<List<Artist>> {
        return artistRepository.getBookmarkedArtists()
    }
}