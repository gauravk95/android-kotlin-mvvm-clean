package com.gk.android.data.artist.model

import androidx.room.Entity
import androidx.room.PrimaryKey

const val ARTIST_TABLE_NAME = "table_artist"

@Entity(tableName = ARTIST_TABLE_NAME)
data class ArtistEntity constructor(
    @PrimaryKey val id: String,
    val name: String? = null,
    val disambiguation: String? = null,
    val imageUrl: String? = null,
    val gender: String? = null,
    val type: String? = null,
    val country: String? = null,
    var isBookmarked: Boolean = false
)