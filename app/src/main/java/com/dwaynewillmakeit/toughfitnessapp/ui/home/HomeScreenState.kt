package com.dwaynewillmakeit.toughfitnessapp.ui.home

import com.dwaynewillmakeit.toughfitnessapp.data.local.entity.WorkoutLog

data class HomeScreenState(
    val recentWorkouts: List<WorkoutLog> = emptyList(),
    val upcomingWorkouts: List<WorkoutLog> = emptyList()
)