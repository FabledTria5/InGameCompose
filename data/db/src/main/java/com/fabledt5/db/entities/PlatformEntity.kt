package com.fabledt5.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "platforms_table")
data class PlatformEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "platform_id")
    val platformId: Int,
    @ColumnInfo(name = "platform_name")
    val platformName: String
)
