package com.dwaynewillmakeit.toughfitnessapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dwaynewillmakeit.toughfitnessapp.ui.home.HomeScreen
import com.dwaynewillmakeit.toughfitnessapp.ui.login.LoginScreen
import com.dwaynewillmakeit.toughfitnessapp.ui.navigation.Destinations.CREATE_WORKOUT_ROUTE
import com.dwaynewillmakeit.toughfitnessapp.ui.navigation.Destinations.EXERCISE_LIST
import com.dwaynewillmakeit.toughfitnessapp.ui.navigation.Destinations.HOME_ROUTE
import com.dwaynewillmakeit.toughfitnessapp.ui.navigation.Destinations.LOGIN_ROUTE
import com.dwaynewillmakeit.toughfitnessapp.ui.navigation.Destinations.SELECT_MUSCLE_GROUP_ROUTE
import com.dwaynewillmakeit.toughfitnessapp.ui.workout_log.CreateWorkoutScreen
import com.dwaynewillmakeit.toughfitnessapp.ui.workout_log.ExerciseListScreen
import com.dwaynewillmakeit.toughfitnessapp.ui.workout_log.SelectMuscleGroupScreen

@Composable
fun AppNavGraph(){


    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = LOGIN_ROUTE){

        composable(HOME_ROUTE){
            HomeScreen(navController)
        }

        composable(LOGIN_ROUTE){
            LoginScreen(navController)
        }

        composable(CREATE_WORKOUT_ROUTE){
            CreateWorkoutScreen(navController)
        }

        composable(SELECT_MUSCLE_GROUP_ROUTE){
            SelectMuscleGroupScreen(navController)
        }

        composable(EXERCISE_LIST){
            ExerciseListScreen(navController)
        }

    }
}