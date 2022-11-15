package com.dwaynewillmakeit.toughfitnessapp.ui.exercise_list

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dwaynewillmakeit.toughfitnessapp.common.Resource
import com.dwaynewillmakeit.toughfitnessapp.data.local.entity.Exercise
import com.dwaynewillmakeit.toughfitnessapp.data.local.repository.contract.ExerciseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseViewModel @Inject constructor(private val exerciseRepository: ExerciseRepository) :
    ViewModel() {

    var state by mutableStateOf(ExerciseListState())


    fun fetchExercises(muscleGroup: String) {
        viewModelScope.launch {

            exerciseRepository.getExercises(muscleGroup).collect { result ->

                when (result) {
                    is Resource.Success -> {
                        result.data?.map {
                            ExerciseState(false, it)

                        }?.let {

                            state = state.copy(
                                exercises = it
                            )
                        }
                    }
                    is Resource.Error -> {
                        Log.e(javaClass.name, "Error: " + result.message)
                    }
                    is Resource.Loading -> {
                        state = state.copy(isLoading = result.isLoading)
                    }
                }
            }


        }
    }

    fun fetchExercisesByMuscleGroup(muscleGroup: String) {
//
//        state = state.copy(isLoading = true)

        viewModelScope.launch {

            exerciseRepository.getExercisesBy(muscleGroup).collectLatest {
                state = state.copy(
                    isLoading = false,
                    exercises = it.map { exercise -> ExerciseState(isSelected = false, exercise) })


            }
        }
    }

    fun addSelectedExercise(exercise: Exercise) {

        val currentSelectedExercise = mutableListOf<Exercise>()
        currentSelectedExercise.addAll(state.selectedExercises)
        currentSelectedExercise.add(exercise)

        state = state.copy(
            selectedExercises = currentSelectedExercise
        )
    }
}