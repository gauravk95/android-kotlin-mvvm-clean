package com.gk.android.domain.artist.interactor

import com.gk.android.domain.artist.model.Artist
import com.gk.android.domain.artist.repository.ArtistRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoveBookmarkedArtist(
    private val artistRepository: ArtistRepository
) {
    suspend fun execute(artist: Artist) =
        withContext(Dispatchers.IO) {
            artistRepository.removeBookmarkedArtist(artist)
        }
}