package com.dwaynewillmakeit.toughfitnessapp.ui.exercise_list

import com.dwaynewillmakeit.toughfitnessapp.data.remote.dto.ExerciseDto

data class ExerciseUiState(val isLoading:Boolean=false, val exerciseDtos:List<ExerciseDto> = emptyList(), val error:String="")
