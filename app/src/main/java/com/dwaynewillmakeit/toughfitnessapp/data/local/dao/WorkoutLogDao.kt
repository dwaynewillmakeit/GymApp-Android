package com.dwaynewillmakeit.toughfitnessapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.dwaynewillmakeit.toughfitnessapp.data.local.entity.WorkoutLog
import com.dwaynewillmakeit.toughfitnessapp.data.local.entity.WorkoutLogExercise
import com.dwaynewillmakeit.toughfitnessapp.data.local.entity.WorkoutSet
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutLogDao {

    //Workout Logs
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLog(workoutLog: WorkoutLog)

    @Update
    suspend fun updateLog(workoutLog: WorkoutLog)

    @Query("SELECT * FROM workout_log")
     fun fetchAll(): Flow<List<WorkoutLog>>

    @Query("SELECT * FROM workout_log WHERE start_date_time<= DATE() order by start_date_time desc")
    fun fetchRecentWorkouts():Flow<List<WorkoutLog>>

    @Query("SELECT * FROM workout_log WHERE start_date_time > DATE() order by start_date_time asc")
    fun fetchUpcomingWorkouts():Flow<List<WorkoutLog>>

    @Query("SELECT * FROM workout_log WHERE guid = :workoutLogUUID")
    suspend fun fetchWorkoutLog(workoutLogUUID: String): WorkoutLog









}