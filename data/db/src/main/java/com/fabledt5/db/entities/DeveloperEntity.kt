package com.fabledt5.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "developers_table")
data class DeveloperEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "developer_id")
    val developerId: Int,
    @ColumnInfo(name = "developer_foundation")
    val foundation: Int,
    @ColumnInfo(name = "developer_icon")
    val icon: String,
    @ColumnInfo(name = "developer_key_people")
    val keyPeople: String,
    @ColumnInfo(name = "developer_headquarters")
    val headquarters: String,
    @ColumnInfo(name = "developer_name")
    val developerName: String,
    @ColumnInfo(name = "developer_preview")
    val preview: String,
    @ColumnInfo(name = "developer_website")
    val website: String,
)