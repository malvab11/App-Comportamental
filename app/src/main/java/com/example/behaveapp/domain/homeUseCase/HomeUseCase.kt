package com.example.behaveapp.domain.homeUseCase

import com.example.behaveapp.data.models.Actividades
import com.example.behaveapp.data.models.ActividadesResponse
import com.example.behaveapp.data.models.CreateResponse
import com.example.behaveapp.data.models.TipoActividades
import com.example.behaveapp.data.repository.RoomRepository
import com.example.behaveapp.data.repository.ServicesRepository
import com.example.behaveapp.data.room.mappers.*
import javax.inject.Inject

class HomeUseCase @Inject constructor(
    private val servicesRepository: ServicesRepository,
    private val roomRepository: RoomRepository
) {
    suspend fun getTipoActividades(accion: String,idUsuario: Int): ActividadesResponse<TipoActividades>? {
        val response = servicesRepository.getTipoActividades(accion = accion, idUsuario = idUsuario)
        val tipos = response?.data ?: emptyList()

        roomRepository.clearTipos()
        roomRepository.insertarTipos(tipos.map { it.toEntity() })

        return response
    }

    suspend fun getActividadesList(accion: String, idUsuario: Int): ActividadesResponse<Actividades>? {
        val response = servicesRepository.getActividadesList(accion = accion, idUsuario = idUsuario)
        val actividades = response?.data ?: emptyList()

        roomRepository.clearActividades()
        roomRepository.insertActividades(actividades.map { it.toEntity() })

        return response
    }

    suspend fun saveActividad(
        tipoActividad: Int,
        tutor: Int,
        titulo: String,
        puntaje: Int
    ): CreateResponse? =
        servicesRepository.saveActivities(tipoActividad, tutor, titulo, puntaje)

    suspend fun editActividad(
        idActividad: Int,
        tipoActividad: Int,
        tutor: Int,
        titulo: String,
        puntaje: Int,
        eliminado: Int
    ): CreateResponse? =
        servicesRepository.editActivities(idActividad, tipoActividad, tutor, titulo, puntaje, eliminado)

    suspend fun deleteActivity(accion: String, idActividad: Int): CreateResponse? = servicesRepository.deleteActivity(accion = accion, idActividad = idActividad)

    //Room
    suspend fun getActividadesFromRoom() = roomRepository.getAllActividades()
    suspend fun getTiposFromRoom() = roomRepository.getAllTipos()
}