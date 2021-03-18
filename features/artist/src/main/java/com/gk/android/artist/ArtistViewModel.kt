package com.gk.android.artist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.gk.android.artist.adapter.ArtistListAction
import com.gk.android.common.SingleLiveEvent
import com.gk.android.domain.artist.interactor.BookmarkArtist
import com.gk.android.domain.artist.interactor.SearchArtist
import com.gk.android.domain.artist.model.Artist
import com.gk.android.ui_components.viewmodel.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

const val ARTIST_PAGING_SIZE = 15
const val DEFAULT_QUERY = "Justin"

class ArtistViewModel constructor(
    private val searchArtist: SearchArtist,
    private val bookmarkArtist: BookmarkArtist
) : BaseViewModel() {

    private var searchJob: Job? = null
    private var currentQuery: String? = null

    val artistsData = MutableLiveData<PagingData<Artist>>()
    val goToDetails = SingleLiveEvent<Artist>()

    init {
        if (currentQuery.isNullOrBlank()) search(DEFAULT_QUERY)
    }

    fun artistItemClick(action: ArtistListAction) {
        when (action) {
            is ArtistListAction.ItemClick -> {
                goToDetails.value = action.item
            }
            is ArtistListAction.BookmarkAction -> {
                bookmarkArtist(action.item)
            }
        }
    }

    private fun bookmarkArtist(artist: Artist) = viewModelScope.launch {
        bookmarkArtist.execute(artist)
        toastMsg.value = R.string.bookmarked
    }

    fun search(query: String?) {
        searchJob?.cancel()
        searchJob = searchItems(query)
    }

    private fun searchItems(query: String?) = viewModelScope.launch {
        if (query == null) return@launch
        if (query == currentQuery) return@launch

        currentQuery = query
        searchArtist.execute(query, ARTIST_PAGING_SIZE)
            .cachedIn(viewModelScope).collect {
                artistsData.value = it
            }
    }
}