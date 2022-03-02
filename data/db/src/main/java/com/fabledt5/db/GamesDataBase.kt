package com.fabledt5.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fabledt5.db.dao.HotGamesDao
import com.fabledt5.db.dao.PlatformsDao
import com.fabledt5.db.entities.HotGameEntity
import com.fabledt5.db.entities.PlatformEntity

@Database(
    entities = [HotGameEntity::class, PlatformEntity::class],
    version = 1,
    exportSchema = false
)
abstract class GamesDataBase : RoomDatabase() {
    abstract fun hotGamesDao(): HotGamesDao
    abstract fun platformsDao(): PlatformsDao
}