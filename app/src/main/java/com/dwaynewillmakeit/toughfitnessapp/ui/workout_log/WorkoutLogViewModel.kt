package com.dwaynewillmakeit.toughfitnessapp.ui.workout_log

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dwaynewillmakeit.toughfitnessapp.data.local.entity.Exercise
import com.dwaynewillmakeit.toughfitnessapp.data.local.entity.WorkoutLog
import com.dwaynewillmakeit.toughfitnessapp.data.local.entity.WorkoutLogExercise
import com.dwaynewillmakeit.toughfitnessapp.data.local.entity.WorkoutSet
import com.dwaynewillmakeit.toughfitnessapp.data.local.repository.contract.WorkoutLogExerciseRepository
import com.dwaynewillmakeit.toughfitnessapp.data.local.repository.contract.WorkoutLogRepository
import com.dwaynewillmakeit.toughfitnessapp.data.local.repository.contract.WorkoutSetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*
import javax.inject.Inject

@HiltViewModel
class WorkoutLogViewModel @Inject constructor(
    private val workoutLogRepository: WorkoutLogRepository,
    private val workoutSetRepository: WorkoutSetRepository,
    private val workoutLogExerciseRepository: WorkoutLogExerciseRepository
) :
    ViewModel() {

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

    fun setWorkoutLogUUID(uuid: String) {
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

        var lastExercisePosition = state.lastExerciseIndex

        exercises.map { exercise ->

            val exerciseUUID = UUID.randomUUID().toString()

            val workoutLogExerciseState = WorkoutLogExerciseState(
                workoutLogExercise = WorkoutLogExercise(
                    exerciseUUID,
                    state.workoutLogUUID,
                    exercise.id,
                    exercise.name,
                    ++lastExercisePosition
                )
            )

            exerciseMap[exerciseUUID] = workoutLogExerciseState

        }

        state = state.copy(lastExerciseIndex = lastExercisePosition)

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

        val workoutSet =
            WorkoutSet(workoutSetUUID, workOutLogExerciseUUID, setCount, weight, repCount, notes, 0)

        viewModelScope.launch {

            saveWorkoutLog()

            workoutLogExerciseRepository.insert(state.workoutLogExercises[workOutLogExerciseUUID]!!.workoutLogExercise)

            workoutSetRepository.insert(workoutSet)

            state.workoutLogExercises[workOutLogExerciseUUID]!!.workoutSets[workoutSetUUID] =
                workoutSet

        }

    }

    private suspend fun saveWorkoutLog() {
        val workoutLog = WorkoutLog(
            state.workoutLogUUID,
            state.workoutName,
            LocalDateTime.of(state.startDate, state.startTime),
            state.notes,
            LocalDateTime.now()
        )

        workoutLogRepository.insertWorkoutLog(workoutLog)
        state = state.copy(isAlreadyPersisted = true)
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

        val workoutSet =
            WorkoutSet(workoutSetUUID, workOutLogExerciseUUID, setCount, weight, repCount, notes, 0)

        state.workoutLogExercises[workOutLogExerciseUUID]!!.workoutSets[workoutSetUUID] = workoutSet

        viewModelScope.launch {

            workoutSetRepository.insert(workoutSet)
        }

    }

    fun removeWorkoutSet(workOutLogExerciseUUID: String, workOutSetUUID: String) {

        viewModelScope.launch {

            workoutSetRepository.delete(state.workoutLogExercises[workOutLogExerciseUUID]!!.workoutSets[workOutSetUUID]!!)
            state.workoutLogExercises[workOutLogExerciseUUID]?.workoutSets?.remove(workOutSetUUID)

            if (state.workoutLogExercises[workOutLogExerciseUUID]!!.workoutSets.isEmpty()) {

                workoutLogExerciseRepository.delete(state.workoutLogExercises[workOutLogExerciseUUID]!!.workoutLogExercise)

                state.workoutLogExercises.remove(workOutLogExerciseUUID)

                return@launch
            }

            sortWorkOutSetsBySetCount(
                workOutLogExerciseUUID
            )
        }

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

        val workoutSets =
            state.workoutLogExercises[workOutLogExerciseUUID]!!.workoutSets.map { it.value }

        viewModelScope.launch {

            workoutSetRepository.insert(workoutSets)
        }

    }

    fun setStartDate(date: LocalDate) {
        state = state.copy(startDate = date)
    }

    fun setStartTime(time: LocalTime) {
        state = state.copy(startTime = time)
    }

    fun saveWorkout() {

        val workoutLog = WorkoutLog(
            state.workoutLogUUID,
            state.workoutName,
            LocalDateTime.of(state.startDate, state.startTime),
            state.notes,
            LocalDateTime.now()
        )

        val workoutSets = mutableListOf<WorkoutSet>()
        val workoutLogExercises = mutableListOf<WorkoutLogExercise>()


        state.workoutLogExercises.forEach { workoutLogExercisesMap ->

            val workoutLogExercise = workoutLogExercisesMap.value.workoutLogExercise

            workoutLogExercises.add(workoutLogExercise)

            workoutLogExercisesMap.value.workoutSets.forEach { workoutSet ->
                workoutSets.add(workoutSet.value)
            }


        }

        viewModelScope.launch {

            workoutLogRepository.insertWorkoutLog(workoutLog)
            workoutLogExerciseRepository.insert(workoutLogExercises)
            workoutSetRepository.insert(workoutSets)

            state = state.copy(savedSuccessful = true, isAlreadyPersisted = true)
        }

    }

    fun fetchWorkOutLog(workoutLogUUID: String) {


        viewModelScope.launch {

            val workoutLog = workoutLogRepository.fetchWorkoutLog(workoutLogUUID)

            workoutLog.let {

                setIsAlreadyPersisted()

                val exerciseMapState = mutableStateMapOf<String, WorkoutLogExerciseState>()

                val workoutLogExercises =
                    workoutLogExerciseRepository.fetchWorkoutLogExercise(workoutLog.guid)

                var lastExerciseIndex = 1;


                workoutLogExercises.sortedBy { exercise -> exercise.index }
                    .forEach { workoutLogExercise ->

                        val workoutSetsMap = mutableStateMapOf<String, WorkoutSet>()

                        lastExerciseIndex = workoutLogExercise.index

                        val workoutSets =
                            workoutSetRepository.fetchWorkoutSets(workoutLogExercise.guid)
                        var lastSetCount = 0;

                        workoutSets.sortedBy { workoutSet -> workoutSet.set }.forEach {

                            workoutSetsMap[it.guid] = it

                            lastSetCount = it.set
                        }

                        exerciseMapState[workoutLogExercise.guid] =
                            WorkoutLogExerciseState(
                                workoutLogExercise,
                                workoutSets = workoutSetsMap,
                                lastSetCount
                            )
                    }

                state = state.copy(
                    workoutLogUUID = workoutLog.guid,
                    workoutName = workoutLog.name,
                    startDate = workoutLog.startDateTime.toLocalDate(),
                    startTime = workoutLog.startDateTime.toLocalTime(),
                    notes = workoutLog.notes,
                    workoutLogExercises = exerciseMapState,
                    lastExerciseIndex = lastExerciseIndex
                );
            }

        }
    }

    private fun setIsAlreadyPersisted() {
        state = state.copy(
            isAlreadyPersisted = true
        )
    }


}