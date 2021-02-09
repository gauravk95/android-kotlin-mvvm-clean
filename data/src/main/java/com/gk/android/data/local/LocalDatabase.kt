package com.gk.android.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gk.android.data.artist.source.BookmarkArtistDao
import com.gk.android.data.artist.model.ArtistEntity

@Database(entities = [ArtistEntity::class], version = 1)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun bookmarkArtistDao(): BookmarkArtistDao
}