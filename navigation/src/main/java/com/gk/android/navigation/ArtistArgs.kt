package com.gk.android.navigation

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
class ArtistArgs constructor(
    val id: String,
    val name: String? = null,
    val disambiguation: String? = null,
    var isBookmarked: Boolean = false
) : Parcelable