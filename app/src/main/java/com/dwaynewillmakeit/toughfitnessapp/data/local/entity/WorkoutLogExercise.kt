package com.dwaynewillmakeit.toughfitnessapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "workout_log_exercise",
    foreignKeys = [
        ForeignKey(
            entity = WorkoutLog::class,
            childColumns = ["workout_log_guid"],
            parentColumns = ["guid"]
        ),
        ForeignKey(
            entity = Exercise::class,
            childColumns = ["exercise_id"],
            parentColumns = ["id"]
        ),
    ],
    indices = [Index(value = ["guid"]),Index(value = ["workout_log_guid"]),Index(value = ["exercise_id"])]
)
data class WorkoutLogExercise(
    @PrimaryKey val guid: String,
    @ColumnInfo(name = "workout_log_guid") val workoutLogGuid: String,
    @ColumnInfo(name = "exercise_id") val exerciseId: Int,
    @ColumnInfo(name = "exercise_name") val exercise_name: String,
    @ColumnInfo(name = "index") val index:Int

)
