package com.dwaynewillmakeit.toughfitnessapp.data.mapper

import com.dwaynewillmakeit.toughfitnessapp.data.remote.dto.ExerciseDto
import com.dwaynewillmakeit.toughfitnessapp.data.local.entity.Exercise

fun ExerciseDto.toExercise(): Exercise {

    return Exercise(id = id, name = name, imageUrl = imageUrl,muscleGroup = muscleGroup.name)
}
