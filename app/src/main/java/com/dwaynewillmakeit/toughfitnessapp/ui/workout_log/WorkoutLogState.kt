package com.dwaynewillmakeit.toughfitnessapp.ui.workout_log

import androidx.compose.runtime.mutableStateMapOf
import com.dwaynewillmakeit.toughfitnessapp.data.local.entity.WorkoutLogExercise
import com.dwaynewillmakeit.toughfitnessapp.data.local.entity.WorkoutSet
import java.time.LocalDateTime

data class WorkoutLogState(
    val workoutName:String ="",
    val startDateTime: LocalDateTime? = null,
    val notes: String="",
    val workoutLogExercises: MutableMap<String, WorkoutLogExerciseState> = mutableStateMapOf() ,
)

data class WorkoutLogExerciseState(
    val workoutLogExercise: WorkoutLogExercise,
    val workoutSets: MutableMap<String,WorkoutSet> = mutableStateMapOf()
)
