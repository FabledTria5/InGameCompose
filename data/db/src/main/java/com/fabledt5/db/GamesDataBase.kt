package com.fabledt5.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fabledt5.db.dao.FiltersDao
import com.fabledt5.db.dao.HotGamesDao
import com.fabledt5.db.entities.DeveloperEntity
import com.fabledt5.db.entities.GenreEntity
import com.fabledt5.db.entities.HotGameEntity
import com.fabledt5.db.entities.PlatformEntity

@Database(
    entities = [
        HotGameEntity::class,
        PlatformEntity::class,
        DeveloperEntity::class,
        GenreEntity::class
    ],
    version = 8,
    exportSchema = true,
)
abstract class GamesDataBase : RoomDatabase() {
    abstract fun hotGamesDao(): HotGamesDao
    abstract fun filtersDao(): FiltersDao
}