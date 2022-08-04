package com.fabledt5.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.fabledt5.db.dao.FiltersDao
import com.fabledt5.db.dao.GamesDao
import com.fabledt5.db.entities.*
import com.fabledt5.db.entities.refrences.FavoriteGamesRef
import com.fabledt5.db.entities.refrences.PlayedGamesRef
import com.fabledt5.db.utils.Converter

@Database(
    entities = [
        HotGameEntity::class,
        SavedGameEntity::class,
        PlatformEntity::class,
        DeveloperEntity::class,
        GenreEntity::class,
        FavoriteGamesRef::class,
        PlayedGamesRef::class
    ],
    version = 12,
    exportSchema = true
)
@TypeConverters(Converter::class)
abstract class GamesDataBase : RoomDatabase() {
    abstract fun gamesDao(): GamesDao
    abstract fun filtersDao(): FiltersDao
}