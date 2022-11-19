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

    fun setWorkoutLogUUID(uuid:String){
        state = state.copy(workoutLogUUID = uuid)
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

    fun addWorkoutSet(
        workOutLogExerciseUUID: String,
        weight: Float,
        repCount: Int,
        notes: String,
        setCount: Int
    ) {

        val workoutSetUUID = UUID.randomUUID().toString()

        state.workoutLogExercises[workOutLogExerciseUUID]!!.workoutSets[workoutSetUUID] =
            WorkoutSet(workoutSetUUID, workOutLogExerciseUUID, setCount, weight, repCount, notes, 0)

    }

    fun modifyWorkoutSet(
        workOutLogExerciseUUID: String,
        workoutSetUUID: String,
        weight: Float,
        repCount: Int,
        notes: String
    ) {

        val setCount =
            state.workoutLogExercises[workOutLogExerciseUUID]!!.workoutSets[workoutSetUUID]!!.set

        state.workoutLogExercises[workOutLogExerciseUUID]!!.workoutSets[workoutSetUUID] =
            WorkoutSet(workoutSetUUID, workOutLogExerciseUUID, setCount, weight, repCount, notes, 0)


    }

    fun removeWorkoutSet(workOutLogExerciseUUID: String, workOutSetUUID: String) {

        state.workoutLogExercises[workOutLogExerciseUUID]?.workoutSets?.remove(workOutSetUUID)

        sortWorkOutSetsBySetCount(
            workOutLogExerciseUUID
        )


    }


   private fun sortWorkOutSetsBySetCount(
        workOutLogExerciseUUID: String
    ) {

        state.workoutLogExercises[workOutLogExerciseUUID]?.workoutSets!!.toList()
            .sortedBy { (_, workOutSet) -> workOutSet.set }
            .mapIndexed { index, pair ->
                state.workoutLogExercises[workOutLogExerciseUUID]!!.workoutSets[pair.first] =
                    pair.second.copy(set = index + 1)

            }

    }


}