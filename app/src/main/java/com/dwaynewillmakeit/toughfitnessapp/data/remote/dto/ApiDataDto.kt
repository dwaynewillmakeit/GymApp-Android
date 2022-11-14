package com.dwaynewillmakeit.toughfitnessapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ApiDataDto<T>(@SerializedName("data") val data: T)
