package com.dwaynewillmakeit.toughfitnessapp.ui.workout_log

import com.dwaynewillmakeit.toughfitnessapp.data.local.entity.WorkoutLogExercise
import com.dwaynewillmakeit.toughfitnessapp.data.local.entity.WorkoutSet
import java.time.LocalDateTime

data class WorkoutLogState(
    val workoutName:String ="",
    val startDateTime: LocalDateTime? = null,
    val notes: String="",
    val workoutLogExercises: List<WorkoutLogExerciseState> = emptyList(),
)

data class WorkoutLogExerciseState(
    val workoutLogExercise: WorkoutLogExercise,
    val workoutSets: List<WorkoutSet>
)
