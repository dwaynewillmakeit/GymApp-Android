package com.dwaynewillmakeit.toughfitnessapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ExerciseDto(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("image") val imageUrl: String,
    @SerializedName("muscle_group") val muscleGroup: MuscleGroupDto
)
