package com.fabledt5.db.entities.refrences

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_games_table")
data class FavoriteGamesRef(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "game_id")
    val gameId: Int
)
