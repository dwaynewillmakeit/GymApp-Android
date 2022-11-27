package com.dwaynewillmakeit.toughfitnessapp.data.local.repository.contract

import com.dwaynewillmakeit.toughfitnessapp.data.local.entity.WorkoutSet

interface WorkoutSetRepository {

    suspend fun insert(workoutSets: List<WorkoutSet>)
    suspend fun insert(workoutSet:WorkoutSet)
    suspend fun fetchWorkoutSets(workoutExerciseUUID: String): List<WorkoutSet>

    suspend fun delete(workoutSet: WorkoutSet)
}