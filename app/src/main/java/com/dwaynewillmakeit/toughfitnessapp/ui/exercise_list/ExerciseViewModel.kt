package com.dwaynewillmakeit.toughfitnessapp.ui.exercise_list

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dwaynewillmakeit.toughfitnessapp.common.Resource
import com.dwaynewillmakeit.toughfitnessapp.data.local.repository.contract.ExerciseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseViewModel @Inject constructor(private val exerciseRepository: ExerciseRepository) :
    ViewModel() {

    var state by mutableStateOf(ExerciseListState())


    public fun fetchExercises(muscleGroup:String) {
        viewModelScope.launch {

        exerciseRepository.getExercises(muscleGroup).collect{ result ->

            when(result){
                is Resource.Success ->{
                    result.data?.let {
                        state = state.copy(
                            exercises = it
                        )
                    }
                }
                is Resource.Error ->{
                    Log.e(javaClass.name, "Error: "+result.message)
                }
                is Resource.Loading ->{
                    state  =state.copy(isLoading = result.isLoading)
                }
            }
        }



        }
    }
}