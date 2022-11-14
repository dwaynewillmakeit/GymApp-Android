package com.dwaynewillmakeit.toughfitnessapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "workout_log", indices = [Index(value=["guid"])])
data class WorkoutLog(
    @PrimaryKey val guid:String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "start_date_time") val startDateTime: LocalDateTime,
    @ColumnInfo(name = "notes") val notes: String,
    @ColumnInfo(name = "created_on") val createdOn: LocalDateTime

)
