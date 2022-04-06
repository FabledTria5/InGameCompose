package com.fabledt5.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hot_games_table")
data class HotGameEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "game_id")
    val gameId: Int,
    @ColumnInfo(name = "created_at")
    val createdAt: Long,
    @ColumnInfo(name = "game_title")
    val gameTitle: String,
    @ColumnInfo(name = "game_image")
    val gamePoster: String?,
    @ColumnInfo(name = "esrb_rating")
    val gamePEGIRating: String,
    @ColumnInfo(name = "release_year")
    val releaseYear: String,
    @ColumnInfo(name = "game_genres")
    val gameGenres: String
)
