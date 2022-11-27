package com.dwaynewillmakeit.toughfitnessapp.data.local.repository

import com.dwaynewillmakeit.toughfitnessapp.data.local.GymDatabase
import com.dwaynewillmakeit.toughfitnessapp.data.local.entity.WorkoutLog
import com.dwaynewillmakeit.toughfitnessapp.data.local.repository.contract.WorkoutLogRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WorkoutLogRepositoryImpl @Inject constructor(private val gymDatabase: GymDatabase) :
    WorkoutLogRepository {

    private val workoutLogDao = gymDatabase.workoutLogDao

    override suspend fun insertWorkoutLog(
        workoutLog: WorkoutLog
    ) {
        workoutLogDao.insertLog(workoutLog)
    }

    override fun fetchRecentWorkouts(): Flow<List<WorkoutLog>> {

        return workoutLogDao.fetchRecentWorkouts()
    }

    override fun fetchUpcomingWorkouts(): Flow<List<WorkoutLog>> {

        return workoutLogDao.fetchUpcomingWorkouts()
    }

    override suspend fun fetchWorkoutLog(workoutLogUUID: String): WorkoutLog {
        return workoutLogDao.fetchWorkoutLog(workoutLogUUID)
    }
}