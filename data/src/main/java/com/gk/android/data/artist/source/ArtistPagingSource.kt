package com.gk.android.data.artist.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.gk.android.data.artist.toArtistList
import com.gk.android.data.remote.RemoteService
import com.gk.android.data.remote.apiCall
import com.gk.android.domain.Resource
import com.gk.android.domain.artist.model.Artist

const val ARTIST_STARTING_PAGE_INDEX = ""

class ArtistPagingSource(
    private val service: RemoteService,
    private val query: String
) : PagingSource<String, Artist>() {

    override fun getRefreshKey(state: PagingState<String, Artist>): String? {
        return ARTIST_STARTING_PAGE_INDEX
    }

    override suspend fun load(params: LoadParams<String>): LoadResult<String, Artist> {
        val position = params.key ?: ARTIST_STARTING_PAGE_INDEX
        val response = apiCall { service.searchArtists(query, params.loadSize, position) }
            .toResource { it.search().toArtistList() }
        when (response) {
            is Resource.Success -> {
                val data = response.data
                val nextKey = when (data?.hasNext) {
                    true -> data.after
                    else -> null
                }
                return LoadResult.Page(
                    data = data?.artists ?: emptyList(),
                    prevKey = null,
                    nextKey = nextKey
                )
            }
            is Resource.ApiError, is Resource.Error -> {
                return LoadResult.Error(Exception(response.message))
            }
            else -> return LoadResult.Error(Exception("Unsupported load..."))
        }
    }
}