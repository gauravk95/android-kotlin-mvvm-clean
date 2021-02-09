package com.gk.android.data.artist

import com.gk.android.data.ArtistQuery
import com.gk.android.data.ArtistsQuery
import com.gk.android.data.artist.model.ArtistEntity
import com.gk.android.domain.artist.model.Artist
import com.gk.android.domain.artist.model.ArtistList

fun ArtistsQuery.Search?.toArtistList(): ArtistList {
    val list = mutableListOf<Artist>()
    this?.artists()?.apply {
        nodes()?.forEach {
            val fragment = it.fragments().artistBasicFragment()
            list.add(Artist(
                fragment.id(),
                fragment.name(),
                fragment.disambiguation(),
                type = fragment.type()
            ))
        }
        return ArtistList(list, pageInfo().endCursor(), pageInfo().hasNextPage())
    }
    return ArtistList(list)
}

fun ArtistQuery.Node?.toArtist(): Artist? {
    val fragment = this?.fragments()?.artistDetailsFragment() ?: return null
    val imageUrl =
        try {
            fragment.fanArt()?.thumbnails()?.get(0)?.url().toString()
        } catch (e: Exception) {
            null
        }
    return Artist(
        fragment.id(),
        fragment.name(),
        fragment.disambiguation(),
        imageUrl,
        fragment.gender(),
        fragment.type(),
        fragment.country())
}

fun Artist.toArtistEntity(): ArtistEntity {
    return ArtistEntity(id, name, disambiguation, imageUrl, gender, type, country, isBookmarked)
}

fun ArtistEntity.toArtist(): Artist {
    return Artist(id, name, disambiguation, imageUrl, gender, type, country, isBookmarked)
}
