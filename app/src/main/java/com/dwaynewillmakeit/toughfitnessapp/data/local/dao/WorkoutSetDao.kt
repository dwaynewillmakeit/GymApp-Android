package com.dwaynewillmakeit.toughfitnessapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.dwaynewillmakeit.toughfitnessapp.data.local.entity.WorkoutSet
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutSetDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSets(workoutSets: List<WorkoutSet>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(workoutSet: WorkoutSet)

    @Update
    suspend fun updateSets(workoutSets: List<WorkoutSet>)

    @Query("SELECT * FROM workout_set WHERE workout_exercise_guid =:workoutLogExerciseGuid")
    fun find(workoutLogExerciseGuid: String): Flow<List<WorkoutSet>>

    @Query("SELECT * FROM workout_set WHERE workout_exercise_guid = :workoutExerciseUUID")
    suspend fun fetchWorkoutSets(workoutExerciseUUID:String):List<WorkoutSet>

    @Delete
    suspend fun delete(workoutSet: WorkoutSet)
}