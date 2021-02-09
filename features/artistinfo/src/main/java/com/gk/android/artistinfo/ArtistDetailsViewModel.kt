package com.gk.android.artistinfo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gk.android.common.SingleLiveEvent
import com.gk.android.domain.Resource
import com.gk.android.domain.artist.interactor.BookmarkArtist
import com.gk.android.domain.artist.interactor.GetArtistDetails
import com.gk.android.domain.artist.model.Artist
import com.gk.android.features.artistinfo.R
import com.gk.android.navigation.ArtistArgs
import kotlinx.coroutines.launch

class ArtistDetailsViewModel constructor(
    private val getArtistDetails: GetArtistDetails,
    private val bookmarkArtist: BookmarkArtist
) : ViewModel() {

    val toastMsg = SingleLiveEvent<Int>()
    val artist = MutableLiveData<Artist>()

    fun loadArtist(args: ArtistArgs) = viewModelScope.launch {
        when (val resource = getArtistDetails.execute(args.id)) {
            is Resource.Success -> artist.value = resource.data
            else -> toastMsg.value = R.string.error_txt_something_went_wrong
        }
    }

    fun bookmarkArtist() = viewModelScope.launch {
        artist.value?.apply {
            bookmarkArtist.execute(this)
            toastMsg.value = R.string.bookmarked
        }
    }
}