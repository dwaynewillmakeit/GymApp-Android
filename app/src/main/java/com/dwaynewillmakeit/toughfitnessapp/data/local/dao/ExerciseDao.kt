package com.dwaynewillmakeit.toughfitnessapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dwaynewillmakeit.toughfitnessapp.data.local.entity.Exercise
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExercises(exercises: List<Exercise>)

    @Query(
        """
        SELECT * FROM exercises
        WHERE lower(muscle_group) = lower(:muscleGroupName);
        """
    )
    fun find(muscleGroupName: String): Flow<List<Exercise>>
}