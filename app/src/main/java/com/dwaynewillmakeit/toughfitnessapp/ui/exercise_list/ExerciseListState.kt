package com.dwaynewillmakeit.toughfitnessapp.ui.exercise_list

import com.dwaynewillmakeit.toughfitnessapp.data.local.entity.Exercise

data class ExerciseListState(
    val exercises: List<Exercise> = emptyList(),
    val isLoading: Boolean = false,
)
