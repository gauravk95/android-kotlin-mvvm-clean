package com.gk.android.artist.util

import android.app.Activity
import com.gk.android.domain.artist.model.Artist
import com.gk.android.navigation.Actions
import com.gk.android.navigation.ArtistArgs

fun Activity.launchArtistDetails(it: Artist?) {
    if (it == null) return
    val intent = Actions.openArtistDetailsIntent(this, ArtistArgs(it.id, it.name, it.disambiguation))
    startActivity(intent)
}