package com.dwaynewillmakeit.toughfitnessapp.data.local.repository.contract

import com.dwaynewillmakeit.toughfitnessapp.data.local.entity.WorkoutLog
import com.dwaynewillmakeit.toughfitnessapp.data.local.entity.WorkoutLogExercise
import com.dwaynewillmakeit.toughfitnessapp.data.local.entity.WorkoutSet

interface WorkoutLogRepository {

    suspend fun insertWorkoutLog(
        workoutLog: WorkoutLog,
        workoutLogExercises: List<WorkoutLogExercise>,
        workoutSets: List<WorkoutSet>
    )
}