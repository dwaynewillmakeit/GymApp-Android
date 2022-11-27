package com.dwaynewillmakeit.toughfitnessapp.data.local.repository.contract

import com.dwaynewillmakeit.toughfitnessapp.data.local.entity.WorkoutLog
import com.dwaynewillmakeit.toughfitnessapp.data.local.entity.WorkoutLogExercise
import com.dwaynewillmakeit.toughfitnessapp.data.local.entity.WorkoutSet
import kotlinx.coroutines.flow.Flow

interface WorkoutLogRepository {

    suspend fun insertWorkoutLog(
        workoutLog: WorkoutLog
    )

    fun fetchRecentWorkouts(): Flow<List<WorkoutLog>>
    fun fetchUpcomingWorkouts(): Flow<List<WorkoutLog>>
    suspend fun fetchWorkoutLog(workoutLogUUID: String): WorkoutLog
}