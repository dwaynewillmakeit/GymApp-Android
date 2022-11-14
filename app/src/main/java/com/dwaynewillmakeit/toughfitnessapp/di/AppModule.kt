package com.dwaynewillmakeit.toughfitnessapp.di

import android.app.Application
import androidx.room.Room
import com.dwaynewillmakeit.toughfitnessapp.common.Constants
import com.dwaynewillmakeit.toughfitnessapp.data.local.GymDatabase
import com.dwaynewillmakeit.toughfitnessapp.data.remote.GymApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesGymApi(): GymApi {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create()
    }

    @Provides
    @Singleton
    fun provideGymDatabase(app: Application): GymDatabase{
        return Room.databaseBuilder(
            app,
            GymDatabase::class.java,
            "gymdb.db"
        ).build()
    }

  
}
