package com.gk.android.domain.artist.model

data class Artist constructor(
    val id: String,
    val name: String? = null,
    val disambiguation: String? = null,
    val imageUrl: String? = null,
    val gender: String? = null,
    val type: String? = null,
    val country: String? = null,
    var isBookmarked: Boolean = false
)