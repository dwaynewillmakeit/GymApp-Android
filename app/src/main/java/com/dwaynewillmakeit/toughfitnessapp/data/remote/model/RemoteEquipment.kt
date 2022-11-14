package com.dwaynewillmakeit.toughfitnessapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class RemoteEquipment(

    @SerializedName("id")
    val id: Long,

    @SerializedName("name")
    val name: String
)
