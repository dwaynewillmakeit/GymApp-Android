package com.dwaynewillmakeit.toughfitnessapp.data.remote.repository.contract

import com.dwaynewillmakeit.toughfitnessapp.common.Resource
import com.dwaynewillmakeit.toughfitnessapp.data.local.entity.Exercise
import kotlinx.coroutines.flow.Flow

interface RemoteExerciseRepository {
    suspend fun getExercises(): Resource<List<Exercise>>

}