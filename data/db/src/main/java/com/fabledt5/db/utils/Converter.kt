package com.fabledt5.db.utils

import androidx.room.TypeConverter
import com.fabledt5.domain.model.GameType

class Converter {

    @TypeConverter
    fun convertToEntity(gameType: GameType): Int = gameType.ordinal

    @TypeConverter
    fun convertFromEntity(gameTypeOrdinal: Int): GameType =
        enumValues<GameType>()[gameTypeOrdinal]

}