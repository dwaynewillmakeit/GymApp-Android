package com.dwaynewillmakeit.toughfitnessapp.data.local.repository

import com.dwaynewillmakeit.toughfitnessapp.data.local.GymDatabase
import com.dwaynewillmakeit.toughfitnessapp.data.local.entity.WorkoutLogExercise
import com.dwaynewillmakeit.toughfitnessapp.data.local.repository.contract.WorkoutLogExerciseRepository
import javax.inject.Inject

class WorkoutLogExerciseRepositoryImpl @Inject constructor(private val gymDatabase: GymDatabase):WorkoutLogExerciseRepository {

    private val workoutLogExerciseDao = gymDatabase.workoutLogExerciseDao

    override suspend fun fetchWorkoutLogExercise(workoutLogUUID: String): List<WorkoutLogExercise> {
       return workoutLogExerciseDao.fetchWorkoutLogExercise(workoutLogUUID)
    }

    override suspend fun insert(workoutLogExercises: List<WorkoutLogExercise>) {
       workoutLogExerciseDao.insertExercise(workoutLogExercises)
    }

    override suspend fun insert(workoutLogExercise: WorkoutLogExercise) {
       workoutLogExerciseDao.insert(workoutLogExercise)
    }

    override suspend fun delete(workoutLogExercise: WorkoutLogExercise) {
       workoutLogExerciseDao.delete(workoutLogExercise)
    }


}