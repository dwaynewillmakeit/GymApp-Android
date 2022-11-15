package com.dwaynewillmakeit.toughfitnessapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "exercises", indices = [Index(value = ["id"])])
@kotlinx.serialization.Serializable
data class Exercise(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name= "image_url") val imageUrl: String,
    @ColumnInfo(name = "muscle_group")val muscleGroup: String
)