package com.fabledt5.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fabledt5.db.dao.HotGamesDao
import com.fabledt5.db.entities.HotGameEntity

@Database(entities = [HotGameEntity::class], version = 1, exportSchema = false)
abstract class GamesDataBase : RoomDatabase() {
    abstract fun hotGamesDao(): HotGamesDao
}