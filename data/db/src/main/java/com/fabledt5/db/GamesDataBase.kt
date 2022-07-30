package com.fabledt5.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.fabledt5.db.dao.FiltersDao
import com.fabledt5.db.dao.GamesDao
import com.fabledt5.db.entities.*
import com.fabledt5.db.utils.Converter

@Database(
    entities = [
        HotGameEntity::class,
        SavedGameEntity::class,
        PlatformEntity::class,
        DeveloperEntity::class,
        GenreEntity::class
    ],
    version = 10,
    exportSchema = true
)
@TypeConverters(Converter::class)
abstract class GamesDataBase : RoomDatabase() {
    abstract fun hotGamesDao(): GamesDao
    abstract fun filtersDao(): FiltersDao
}