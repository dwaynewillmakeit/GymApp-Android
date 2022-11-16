package com.dwaynewillmakeit.toughfitnessapp.ui.workout_log

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.dwaynewillmakeit.toughfitnessapp.data.local.entity.Exercise
import com.dwaynewillmakeit.toughfitnessapp.data.local.entity.WorkoutLogExercise
import com.dwaynewillmakeit.toughfitnessapp.data.local.entity.WorkoutSet
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
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

    private fun fetchWorkoutLog() {

        state
    }

    fun addExercisesToLog(exercises: List<Exercise>) {

        val exerciseList = mutableStateMapOf<String, WorkoutLogExerciseState>()
        exerciseList.putAll(state.workoutLogExercises)
        exerciseList.putAll(buildWorkoutLogExerciseState(exercises))

        state = state.copy(
            workoutLogExercises = exerciseList
        )
    }

    private fun buildWorkoutLogExerciseState(exercises: List<Exercise>): Map<String, WorkoutLogExerciseState> {

        val exerciseMap = mutableMapOf<String, WorkoutLogExerciseState>()

        exercises.map { exercise ->

            val exerciseUUID = UUID.randomUUID().toString()

            val workoutLogExercise = WorkoutLogExerciseState(
                workoutLogExercise = WorkoutLogExercise(
                    exerciseUUID,
                    "NEED TO SET",
                    exercise.id,
                    exercise.name,
                    0
                )
            )

            exerciseMap[exerciseUUID] = workoutLogExercise

        }

        return exerciseMap
    }

    fun addSet(workOutLogExerciseUUID: String) {

        val workoutSetUUID = UUID.randomUUID().toString()

        state.workoutLogExercises[workOutLogExerciseUUID]!!.workoutSets[workoutSetUUID] = WorkoutSet(workoutSetUUID, "NO IMPLEMENTED", 0, 2F, 5, "", 0)

    }

    fun removeSet(workOutLogExerciseUUID: String,workOutSetUUID:String){

       state.workoutLogExercises[workOutLogExerciseUUID]?.workoutSets?.remove(workOutSetUUID)

    }


}