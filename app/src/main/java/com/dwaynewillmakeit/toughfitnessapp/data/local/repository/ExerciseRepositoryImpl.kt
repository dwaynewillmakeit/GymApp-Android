package com.dwaynewillmakeit.toughfitnessapp.data.local.repository

import com.dwaynewillmakeit.toughfitnessapp.common.Resource
import com.dwaynewillmakeit.toughfitnessapp.data.local.GymDatabase
import com.dwaynewillmakeit.toughfitnessapp.data.local.entity.Exercise
import com.dwaynewillmakeit.toughfitnessapp.data.local.repository.contract.ExerciseRepository
import com.dwaynewillmakeit.toughfitnessapp.data.remote.repository.contract.RemoteExerciseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ExerciseRepositoryImpl @Inject constructor(
    private val gymDatabase: GymDatabase,
    private val remoteExerciseRepository: RemoteExerciseRepository
) : ExerciseRepository {

    private val exerciseDao = gymDatabase.exerciseDao

    override suspend fun getExercises(muscleGroupName: String): Flow<Resource<List<Exercise>>> {

        return flow {

            emit(Resource.Loading(isLoading = true))

            val exercises = exerciseDao.find(muscleGroupName)

            exercises.collect {

                if (it.isNotEmpty()) {

                    emit(Resource.Loading(isLoading = false))

                    emit(Resource.Success(data = it))

                } else {

                    remoteExerciseRepository.getExercises()

                }
            }

        }
    }
}