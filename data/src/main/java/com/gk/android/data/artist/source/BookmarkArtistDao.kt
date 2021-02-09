package com.gk.android.data.artist.source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Delete
import androidx.room.Query
import com.gk.android.data.artist.model.ArtistEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkArtistDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun bookmarkArtist(artist: ArtistEntity)

    @Delete
    suspend fun removeBookmarkedArtist(artist: ArtistEntity)

    @Query("SELECT * from table_artist")
    fun getArtists(): Flow<List<ArtistEntity>>
}