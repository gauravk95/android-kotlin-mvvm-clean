package com.gk.android.artist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gk.android.artist.adapter.ArtistListAction
import com.gk.android.common.SingleLiveEvent
import com.gk.android.domain.artist.interactor.GetBookmarkedArtists
import com.gk.android.domain.artist.interactor.RemoveBookmarkedArtist
import com.gk.android.domain.artist.model.Artist
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class BookmarkArtistViewModel constructor(
    private val getBookmarkedArtists: GetBookmarkedArtists,
    private val removeBookmark: RemoveBookmarkedArtist
) : ViewModel() {

    val artists = MutableLiveData<List<Artist>>()
    val goToDetails = SingleLiveEvent<Artist>()
    val toastMsg = SingleLiveEvent<Int>()
    val state = MutableLiveData<BookmarkArtistState>()

    init {
        viewModelScope.launch {
            getBookmarkedArtists.execute().collect {
                when (it.isNullOrEmpty()) {
                    true -> state.value = BookmarkArtistState.EMPTY
                    false -> {
                        state.value = BookmarkArtistState.SHOW_BOOKMARKS
                        artists.value = it
                    }
                }
            }
        }
    }

    fun artistItemClick(action: ArtistListAction) {
        when (action) {
            is ArtistListAction.ItemClick -> {
                goToDetails.value = action.item
            }
            is ArtistListAction.BookmarkAction -> {
                val artist = action.item
                if (artist.isBookmarked) removeBookmarkedArtist(artist)
            }
        }
    }

    private fun removeBookmarkedArtist(artist: Artist) = viewModelScope.launch {
        removeBookmark.execute(artist)
        toastMsg.value = R.string.remove_bookmark
    }
}