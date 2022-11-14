package com.dwaynewillmakeit.toughfitnessapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.dwaynewillmakeit.toughfitnessapp.data.local.entity.WorkoutLog
import com.dwaynewillmakeit.toughfitnessapp.data.local.entity.WorkoutLogExercise
import com.dwaynewillmakeit.toughfitnessapp.data.local.entity.WorkoutSet
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutLogDao {

    //Workout Logs
    @Insert
    suspend fun insertLog(workoutLog: WorkoutLog)

    @Update
    suspend fun updateLog(workoutLog: WorkoutLog)

    @Query("SELECT * FROM workout_log")
     fun fetchAll(): Flow<List<WorkoutLog>>

    //Workout Log Exercises
    @Insert
    suspend fun insertExercise(workoutLogExercises: List<WorkoutLogExercise>)

    @Insert
    suspend fun updateExercise(workoutLogExercises: List<WorkoutLogExercise>)


    //Workout Set
    @Insert
    suspend fun insertSets(workoutSets: List<WorkoutSet>)

    @Update
    suspend fun updateSets(workoutSets: List<WorkoutSet>)

    @Query("SELECT * FROM workout_set WHERE workout_exercise_guid =:workoutLogExerciseGuid")
    fun find(workoutLogExerciseGuid: String):Flow<List<WorkoutSet>>
}