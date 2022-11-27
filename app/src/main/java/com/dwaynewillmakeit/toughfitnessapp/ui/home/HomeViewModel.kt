package com.dwaynewillmakeit.toughfitnessapp.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dwaynewillmakeit.toughfitnessapp.data.local.repository.contract.WorkoutLogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val workoutLogRepository: WorkoutLogRepository) :
    ViewModel() {

    var state by mutableStateOf(HomeScreenState())

    init {
        fetchRecentWorkout()
        fetchUpcomingWorkout()
    }

    private fun fetchRecentWorkout() {
        val recentWorkouts = workoutLogRepository.fetchRecentWorkouts()


        viewModelScope.launch {

            recentWorkouts.collect {
                state = state.copy(recentWorkouts = it)
            }
        }

    }

    private fun fetchUpcomingWorkout() {
        val upcomingWorkouts = workoutLogRepository.fetchUpcomingWorkouts()


        viewModelScope.launch {

            upcomingWorkouts.collect {
                state = state.copy(upcomingWorkouts = it)
            }
        }

    }

}