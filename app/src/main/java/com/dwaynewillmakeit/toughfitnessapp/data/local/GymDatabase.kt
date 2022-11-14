package com.dwaynewillmakeit.toughfitnessapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.dwaynewillmakeit.toughfitnessapp.data.local.converter.DateConverter
import com.dwaynewillmakeit.toughfitnessapp.data.local.dao.ExerciseDao
import com.dwaynewillmakeit.toughfitnessapp.data.local.dao.WorkoutLogDao
import com.dwaynewillmakeit.toughfitnessapp.data.local.entity.Exercise
import com.dwaynewillmakeit.toughfitnessapp.data.local.entity.WorkoutLog
import com.dwaynewillmakeit.toughfitnessapp.data.local.entity.WorkoutLogExercise
import com.dwaynewillmakeit.toughfitnessapp.data.local.entity.WorkoutSet

@Database(
    entities = [Exercise::class, WorkoutLog::class, WorkoutLogExercise::class, WorkoutSet::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(DateConverter::class)
abstract class GymDatabase : RoomDatabase() {

    abstract val exerciseDao: ExerciseDao
    abstract val workoutLogDao: WorkoutLogDao
}