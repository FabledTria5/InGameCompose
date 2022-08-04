package com.fabledt5.db.entities.refrences

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "played_games_table")
data class PlayedGamesRef(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "game_id")
    val gameId: Int
)