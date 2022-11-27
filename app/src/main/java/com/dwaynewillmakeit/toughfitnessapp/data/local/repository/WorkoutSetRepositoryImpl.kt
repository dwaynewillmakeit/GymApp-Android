package com.dwaynewillmakeit.toughfitnessapp.data.local.repository

import com.dwaynewillmakeit.toughfitnessapp.data.local.GymDatabase
import com.dwaynewillmakeit.toughfitnessapp.data.local.entity.WorkoutSet
import com.dwaynewillmakeit.toughfitnessapp.data.local.repository.contract.WorkoutSetRepository
import javax.inject.Inject

class WorkoutSetRepositoryImpl @Inject constructor(private val gymDatabase: GymDatabase):WorkoutSetRepository {

    private val workoutSetDao = gymDatabase.workoutSetDao
    override suspend fun insert(workoutSets: List<WorkoutSet>) {
        workoutSetDao.insertSets(workoutSets)
    }

    override suspend fun insert(workoutSet: WorkoutSet) {
        workoutSetDao.insert(workoutSet)
    }

    override suspend fun fetchWorkoutSets(workoutExerciseUUID: String): List<WorkoutSet> {
      return  workoutSetDao.fetchWorkoutSets(workoutExerciseUUID)
    }

    override suspend fun delete(workoutSet: WorkoutSet){
        workoutSetDao.delete(workoutSet)
    }
}