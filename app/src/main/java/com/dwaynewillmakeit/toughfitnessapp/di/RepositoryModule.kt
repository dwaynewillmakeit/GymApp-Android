package com.dwaynewillmakeit.toughfitnessapp.di

import com.dwaynewillmakeit.toughfitnessapp.data.local.repository.ExerciseRepositoryImpl
import com.dwaynewillmakeit.toughfitnessapp.data.local.repository.WorkoutLogRepositoryImpl
import com.dwaynewillmakeit.toughfitnessapp.data.local.repository.contract.ExerciseRepository
import com.dwaynewillmakeit.toughfitnessapp.data.local.repository.contract.WorkoutLogRepository
import com.dwaynewillmakeit.toughfitnessapp.data.remote.repository.RemoteEquipmentRepositoryImpl
import com.dwaynewillmakeit.toughfitnessapp.data.remote.repository.RemoteExerciseRepositoryImpl
import com.dwaynewillmakeit.toughfitnessapp.data.remote.repository.contract.RemoteEquipmentRepository
import com.dwaynewillmakeit.toughfitnessapp.data.remote.repository.contract.RemoteExerciseRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    //***Remote***//

    @Binds
    @Singleton
    abstract fun bindRemoteExerciseRepository(remoteExerciseRepositoryImpl: RemoteExerciseRepositoryImpl): RemoteExerciseRepository

    @Binds
    @Singleton
    abstract fun bindRemoteEquipmentRepository(remoteEquipmentRepository: RemoteEquipmentRepositoryImpl): RemoteEquipmentRepository


    //***Local***//

    @Binds
    @Singleton
    abstract fun bindExerciseRepository(exerciseRepositoryImpl: ExerciseRepositoryImpl): ExerciseRepository

    @Binds
    @Singleton
    abstract fun bindWorkoutLogRepository(workoutLogRepository: WorkoutLogRepositoryImpl): WorkoutLogRepository
}