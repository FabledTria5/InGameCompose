package com.fabledt5.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_games_table")
data class SavedGameEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "game_id")
    val gameId: Int,
    @ColumnInfo(name = "game_title")
    val gameTitle: String,
    @ColumnInfo(name = "game_poster")
    val gamePoster: String?,
    @ColumnInfo(name = "game_developer")
    val gameDeveloper: String,
    @ColumnInfo(name = "release_date")
    val releaseDate: String
)