package com.dwaynewillmakeit.toughfitnessapp.data.remote.repository

import com.dwaynewillmakeit.toughfitnessapp.data.remote.GymApi
import com.dwaynewillmakeit.toughfitnessapp.data.remote.model.RemoteEquipment
import com.dwaynewillmakeit.toughfitnessapp.data.remote.repository.contract.RemoteEquipmentRepository
import javax.inject.Inject

class RemoteEquipmentRepositoryImpl @Inject constructor(private val gymApi: GymApi):RemoteEquipmentRepository {


    override suspend fun getEquipment():List<RemoteEquipment>{

        return gymApi.getEquipment()
    }
}