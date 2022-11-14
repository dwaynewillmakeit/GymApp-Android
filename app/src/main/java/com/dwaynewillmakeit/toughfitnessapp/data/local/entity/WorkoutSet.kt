package com.dwaynewillmakeit.toughfitnessapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "workout_set",
    foreignKeys = [ForeignKey(
        entity = WorkoutLogExercise::class,
        childColumns = ["workout_exercise_guid"],
        parentColumns = ["guid"]
    )],
    indices = [Index(value = ["workout_exercise_guid"])]
)
data class WorkoutSet(
    @PrimaryKey val guid: String,
    @ColumnInfo(name = "workout_exercise_guid") val workoutExerciseGuid: String,
    @ColumnInfo(name = "set") val set: Int,
    @ColumnInfo(name = "weight") val weight: Float,
    @ColumnInfo(name = "rep_count") val repCount: Int,
    @ColumnInfo(name = "notes") val notes: String,
    @ColumnInfo(name = "index") val index: Int,
)
