package com.dwaynewillmakeit.toughfitnessapp.data.local.repository.contract

import com.dwaynewillmakeit.toughfitnessapp.data.local.entity.WorkoutLogExercise
import com.dwaynewillmakeit.toughfitnessapp.ui.workout_log.WorkoutLogExerciseState

interface WorkoutLogExerciseRepository {

    suspend fun fetchWorkoutLogExercise(workoutLogUUID: String): List<WorkoutLogExercise>

    suspend fun insert( workoutLogExercises: List<WorkoutLogExercise>)
    suspend fun insert(workoutLogExercise: WorkoutLogExercise)
    suspend fun delete(workoutLogExercise: WorkoutLogExercise)

}