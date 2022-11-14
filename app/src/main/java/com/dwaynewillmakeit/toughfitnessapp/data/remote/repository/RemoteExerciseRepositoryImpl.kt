package com.dwaynewillmakeit.toughfitnessapp.data.remote.repository

import com.dwaynewillmakeit.toughfitnessapp.common.Resource
import com.dwaynewillmakeit.toughfitnessapp.data.local.GymDatabase
import com.dwaynewillmakeit.toughfitnessapp.data.local.entity.Exercise
import com.dwaynewillmakeit.toughfitnessapp.data.mapper.toExercise
import com.dwaynewillmakeit.toughfitnessapp.data.remote.GymApi
import com.dwaynewillmakeit.toughfitnessapp.data.remote.repository.contract.RemoteExerciseRepository
import javax.inject.Inject

class RemoteExerciseRepositoryImpl @Inject constructor(
    private val gymApi: GymApi,
    private val gymDatabase: GymDatabase
) : RemoteExerciseRepository {

    private val exerciseDao = gymDatabase.exerciseDao

    override suspend fun getExercises(): Resource<List<Exercise>> {



            val remoteExercises = try {
                //Fetch exercises from API
                gymApi.getExercises()


            } catch (e: java.lang.Exception) {
                e.printStackTrace()

                null
            }

            remoteExercises?.data?.let { exercises ->

                exerciseDao.insertExercises(exercises.map { it.toExercise() })

                return Resource.Success(exercises.map { it.toExercise() })
            }

        return Resource.Error("Unable to get exercises from remote db")



    }
}