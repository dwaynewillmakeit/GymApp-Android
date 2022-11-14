package com.dwaynewillmakeit.toughfitnessapp.data.local.repository.contract

import com.dwaynewillmakeit.toughfitnessapp.common.Resource
import com.dwaynewillmakeit.toughfitnessapp.data.local.entity.Exercise
import kotlinx.coroutines.flow.Flow

interface ExerciseRepository {

    suspend fun getExercises(muscleGroupName:String): Flow<Resource<List<Exercise>>>
}