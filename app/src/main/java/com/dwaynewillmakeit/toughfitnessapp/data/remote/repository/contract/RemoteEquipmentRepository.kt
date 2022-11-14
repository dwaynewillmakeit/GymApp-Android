package com.dwaynewillmakeit.toughfitnessapp.data.remote.repository.contract

import com.dwaynewillmakeit.toughfitnessapp.data.remote.model.RemoteEquipment

interface RemoteEquipmentRepository {
    suspend fun getEquipment(): List<RemoteEquipment>
}