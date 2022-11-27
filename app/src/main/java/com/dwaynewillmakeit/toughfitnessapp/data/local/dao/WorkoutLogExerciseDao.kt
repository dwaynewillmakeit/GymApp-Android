package com.dwaynewillmakeit.toughfitnessapp.data.local.dao

import androidx.room.*
import com.dwaynewillmakeit.toughfitnessapp.data.local.entity.WorkoutLogExercise

@Dao
interface WorkoutLogExerciseDao {

    //Workout Log Exercises
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExercise(workoutLogExercises: List<WorkoutLogExercise>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(workoutLogExercise: WorkoutLogExercise)

    @Update
    suspend fun updateExercise(workoutLogExercises: List<WorkoutLogExercise>)

    @Query("SELECT * FROM workout_log_exercise WHERE workout_log_guid = :workoutLogUUID")
    suspend fun fetchWorkoutLogExercise(workoutLogUUID:String):List<WorkoutLogExercise>

    @Delete
    suspend fun delete(workoutLogExercise: WorkoutLogExercise)
}