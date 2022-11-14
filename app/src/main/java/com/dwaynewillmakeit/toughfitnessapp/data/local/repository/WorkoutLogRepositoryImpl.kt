package com.dwaynewillmakeit.toughfitnessapp.data.local.repository

import com.dwaynewillmakeit.toughfitnessapp.data.local.GymDatabase
import com.dwaynewillmakeit.toughfitnessapp.data.local.entity.WorkoutLog
import com.dwaynewillmakeit.toughfitnessapp.data.local.entity.WorkoutLogExercise
import com.dwaynewillmakeit.toughfitnessapp.data.local.entity.WorkoutSet
import com.dwaynewillmakeit.toughfitnessapp.data.local.repository.contract.WorkoutLogRepository
import javax.inject.Inject

class WorkoutLogRepositoryImpl @Inject constructor(private val gymDatabase: GymDatabase) :
    WorkoutLogRepository {

    private val workoutLogDao = gymDatabase.workoutLogDao

    override suspend fun insertWorkoutLog(
        workoutLog: WorkoutLog,
        workoutLogExercises: List<WorkoutLogExercise>,
        workoutSets: List<WorkoutSet>
    ) {

        workoutLogDao.insertLog(workoutLog)

        workoutLogDao.insertExercise(workoutLogExercises)

        workoutLogDao.insertSets(workoutSets)
    }
}