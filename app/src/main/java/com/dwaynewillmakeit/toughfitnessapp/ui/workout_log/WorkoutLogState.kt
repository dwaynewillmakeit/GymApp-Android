package com.dwaynewillmakeit.toughfitnessapp.ui.workout_log

import androidx.compose.runtime.mutableStateMapOf
import com.dwaynewillmakeit.toughfitnessapp.data.local.entity.WorkoutLogExercise
import com.dwaynewillmakeit.toughfitnessapp.data.local.entity.WorkoutSet
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

data class WorkoutLogState(
    val workoutLogUUID: String = UUID.randomUUID().toString(),
    val workoutName: String = "",
    val startDate: LocalDate = LocalDate.now(),
    val startTime: LocalTime = LocalTime.now(),
    val notes: String = "",
    val workoutLogExercises: MutableMap<String, WorkoutLogExerciseState> = mutableStateMapOf(),
    val savedSuccessful: Boolean? = null,
    val isAlreadyPersisted: Boolean = false,
    val lastExerciseIndex: Int = 0
)

data class WorkoutLogExerciseState(
    val workoutLogExercise: WorkoutLogExercise,
    val workoutSets: MutableMap<String, WorkoutSet> = mutableStateMapOf(),
    val lastSetCount: Int = 0

)
