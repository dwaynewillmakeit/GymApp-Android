package com.dwaynewillmakeit.toughfitnessapp.ui.exercise_list

import com.dwaynewillmakeit.toughfitnessapp.data.local.entity.Exercise

@kotlinx.serialization.Serializable
data class ExerciseListState(
    val exercises: List<ExerciseState> = emptyList(),
    var selectedExercises: List<Exercise> = emptyList(),
    val isLoading: Boolean = false,
)

@kotlinx.serialization.Serializable
data class ExerciseState(
    var isSelected: Boolean = false,
    val exercise: Exercise
)
