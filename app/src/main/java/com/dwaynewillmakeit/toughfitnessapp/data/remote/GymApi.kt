package com.dwaynewillmakeit.toughfitnessapp.data.remote

import com.dwaynewillmakeit.toughfitnessapp.data.remote.dto.ApiDataDto
import com.dwaynewillmakeit.toughfitnessapp.data.remote.dto.ExerciseDto
import com.dwaynewillmakeit.toughfitnessapp.data.remote.model.RemoteEquipment
import retrofit2.http.GET

interface GymApi {

    @GET("api/v1/equipment")
   suspend fun getEquipment():List<RemoteEquipment>

   @GET("api/v1/exercises")
   suspend fun getExercises(): ApiDataDto<List<ExerciseDto>>
}