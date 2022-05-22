package com.fabledt5.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genres_table")
data class GenreEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "genre_id")
    val genreId: Int,
    @ColumnInfo(name = "genre_name")
    val genreName: String
)