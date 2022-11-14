package com.dwaynewillmakeit.toughfitnessapp.ui.workout_log

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.dwaynewillmakeit.toughfitnessapp.data.local.entity.WorkoutLogExercise
import com.dwaynewillmakeit.toughfitnessapp.data.local.entity.WorkoutSet
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WorkoutLogViewModel @Inject constructor() : ViewModel() {

    var state by mutableStateOf(WorkoutLogState())

    init {
        fetchWorkoutLog()
    }

    fun setWorkoutName(name: String) {
        state = state.copy(workoutName = name)
    }

    fun setNotes(notes: String) {
        state = state.copy(notes = notes)
    }

    private fun fetchWorkoutLog(){

        state = state.copy(
            workoutLogExercises = listOf(
                WorkoutLogExerciseState(
                    workoutLogExercise = WorkoutLogExercise("sadsa","sdasd",1,"Barbell Bench Press",0),
                    workoutSets = listOf(
                        WorkoutSet("123450","sdf",1,215F,5,"Lorem ipsum dolor sit amet, consectetur adipiscing elit.",0),
                        WorkoutSet("123450","sdf",1,215F,5,"",0),
                        WorkoutSet("123450","sdf",1,215F,5,"",0),
                    )
                ) ,
                WorkoutLogExerciseState(
                    workoutLogExercise = WorkoutLogExercise("sadsa","sdasd",1,"Barbell Standing Reverse Grip Curl",0),
                    workoutSets = listOf(
                        WorkoutSet("123450","sdf",1,215F,5,"",0),
                        WorkoutSet("123450","sdf",1,215F,5,"Lorem ipsum dolor sit amet, consectetur adipiscing elit.",0),
                        WorkoutSet("123450","sdf",1,215F,5,"",0),
                    )
                ),
                WorkoutLogExerciseState(
                    workoutLogExercise = WorkoutLogExercise("sadsa","sdasd",1,"Dumbbell Bicep Curl",0),
                    workoutSets = listOf(
                        WorkoutSet("123450","sdf",1,215F,5,"",0),
                        WorkoutSet("123450","sdf",1,215F,5,"Lorem ipsum dolor sit amet, consectetur adipiscing elit.",0),
                        WorkoutSet("123450","sdf",1,215F,5,"",0),
                    )
                )
            ),

        )
    }

}