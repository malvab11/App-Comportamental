package com.example.behaveapp.domain.homeUseCase

import android.util.Log
import com.example.behaveapp.data.models.Actividades
import com.example.behaveapp.data.models.ApiResponse
import com.example.behaveapp.data.models.GenericResponse
import com.example.behaveapp.data.models.TipoActividades
import com.example.behaveapp.data.repository.RoomRepository
import com.example.behaveapp.data.repository.ServicesRepository
import com.example.behaveapp.data.room.entity.ActividadEntity
import com.example.behaveapp.data.room.entity.TipoActividadEntity
import javax.inject.Inject

class ActividadesUseCase @Inject constructor(
    private val servicesRepository: ServicesRepository,
    private val roomRepository: RoomRepository
)  {
    suspend fun getTipoActividades(accion: String,idUsuario: Int): ApiResponse<List<TipoActividades>>? {
        val response = servicesRepository.getTipoActividades(accion = accion, idUsuario = idUsuario)
        if (response?.status == "success") {
            val listaTipoActividades = response.data ?: emptyList()
            val tipoActividadEntity = listaTipoActividades.map { TipoActividadEntity(
                id = it.idTipoActividad ?: 0,
                descripcion = it.descripcion ?: "",
                detalle = it.detalle ?: "",
            ) }
            roomRepository.clearTipos()
            roomRepository.insertTipos(tipoActividadEntity)
            Log.i("tipoActividades", roomRepository.getAllTipos().toString())
        }
        return response
    }

    suspend fun getActividadesList(accion: String, idUsuario: Int): ApiResponse<List<Actividades>>? {
        val response = servicesRepository.getActividadesList(accion = accion, idUsuario = idUsuario)
        if (response?.status == "success") {
            val listaActividades = response.data ?: emptyList()
            val actividadesEntity = listaActividades.map {
                ActividadEntity(
                    idActividad = it.idActividad ?: 0,
                    tipoActividad = it.tipoActividad ?: 0,
                    tutor = it.tutor ?: 0,
                    titulo = it.titulo ?: "",
                    descripcion = it.descripcion ?: "",
                    puntaje = it.puntaje ?: 0,
                    eliminado = it.eliminado ?: 0,
                    isSelected = it.isSelected ?: false
                )
            }
            roomRepository.clearActividades()
            roomRepository.insertActividades(actividadesEntity)
            Log.i("actividades", roomRepository.getAllActividades().toString())
        }
        return response
    }

    suspend fun saveActividad(tipoActividad: Int, tutor: Int, titulo: String, puntaje: Int): GenericResponse? =
        servicesRepository.saveActivities(tipoActividad, tutor, titulo, puntaje)

    suspend fun editActividad(idActividad: Int, tipoActividad: Int, tutor: Int, titulo: String, puntaje: Int, eliminado: Int): GenericResponse? =
        servicesRepository.editActivities(idActividad, tipoActividad, tutor, titulo, puntaje, eliminado)

    suspend fun deleteActivity(accion: String, idActividad: Int): GenericResponse? = servicesRepository.deleteActivity(accion = accion, idActividad = idActividad)

    suspend fun getUsuario() = roomRepository.getUsuario()
    suspend fun getActividades() = roomRepository.getAllActividades()
    suspend fun getActividadById(id: Int) = roomRepository.getActividadById(id)
    suspend fun getTipoActividades() = roomRepository.getAllTipos()
}