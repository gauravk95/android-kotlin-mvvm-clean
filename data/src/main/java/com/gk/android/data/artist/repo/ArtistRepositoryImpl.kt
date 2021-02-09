package com.gk.android.data.artist.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.gk.android.data.artist.source.ArtistPagingSource
import com.gk.android.data.artist.toArtist
import com.gk.android.data.local.DataStorage
import com.gk.android.data.remote.RemoteService
import com.gk.android.data.remote.apiCall
import com.gk.android.domain.Resource
import com.gk.android.domain.artist.model.Artist
import com.gk.android.domain.artist.repository.ArtistRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ArtistRepositoryImpl(
    private val service: RemoteService,
    private val dataStorage: DataStorage
) : ArtistRepository {

    override suspend fun searchArtists(searchQuery: String, first: Int): Flow<PagingData<Artist>> {
        return Pager(
            config = PagingConfig(
                pageSize = first,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ArtistPagingSource(service, searchQuery) }
        ).flow
    }

    override suspend fun getArtistDetails(id: String): Resource<Artist?> {
        return apiCall { service.getArtistDetails(id) }
            .toResource { it.node().toArtist() }
    }

    override suspend fun bookmarkArtist(artist: Artist) {
        dataStorage.bookmarkArtist(artist)
    }

    override suspend fun removeBookmarkedArtist(artist: Artist) {
        dataStorage.removeBookmarkedArtist(artist)
    }

    override fun getBookmarkedArtists(): Flow<List<Artist>> {
        return dataStorage.getArtists().map {
            it.map { artistEntity -> artistEntity.toArtist() }
        }
    }
}