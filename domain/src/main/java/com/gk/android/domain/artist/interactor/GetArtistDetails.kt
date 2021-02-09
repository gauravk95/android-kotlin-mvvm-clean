package com.gk.android.domain.artist.interactor

import com.gk.android.domain.Resource
import com.gk.android.domain.artist.model.Artist
import com.gk.android.domain.artist.repository.ArtistRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetArtistDetails(
    private val artistRepository: ArtistRepository
) {
    suspend fun execute(id: String): Resource<Artist?> =
        withContext(Dispatchers.IO) {
            artistRepository.getArtistDetails(id)
        }
}