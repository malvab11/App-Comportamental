package com.example.behaveapp.domain.homeUseCase

import com.example.behaveapp.data.models.Actividades
import com.example.behaveapp.data.models.ApiResponse
import com.example.behaveapp.data.models.GenericResponse
import com.example.behaveapp.data.models.ReporteData
import com.example.behaveapp.data.models.TipoActividades
import com.example.behaveapp.data.repository.RoomRepository
import com.example.behaveapp.data.repository.ServicesRepository
import com.example.behaveapp.data.room.mappers.*
import javax.inject.Inject

class HomeUseCase @Inject constructor(
    private val servicesRepository: ServicesRepository,
    private val roomRepository: RoomRepository
) {

    //Room
    suspend fun getActividadesFromRoom() = roomRepository.getAllActividades()
    suspend fun getTiposFromRoom() = roomRepository.getAllTipos()
}