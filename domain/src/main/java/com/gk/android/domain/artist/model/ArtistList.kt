package com.gk.android.domain.artist.model

data class ArtistList constructor(
    val artists: List<Artist>,
    val after: String? = "",
    val hasNext: Boolean = true
)