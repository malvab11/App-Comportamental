package com.example.behaveapp.domain.homeUseCase

import android.util.Log
import com.example.behaveapp.data.models.ApiResponse
import com.example.behaveapp.data.models.ReporteData
import com.example.behaveapp.data.repository.RoomRepository
import com.example.behaveapp.data.repository.ServicesRepository
import com.example.behaveapp.data.room.entity.ActividadEntity
import com.example.behaveapp.data.room.entity.ActividadUsuarioEntity
import com.example.behaveapp.data.room.entity.AlumnoEntity
import javax.inject.Inject

class ReporteUseCase @Inject constructor(
    private val servicesRepository: ServicesRepository,
    private val roomRepository: RoomRepository
) {
    suspend fun getReporte(
        accion: String,
        idUsuario: Int,
        anio: String,
        mes: String
    ): ApiResponse<ReporteData>? {
        val response = servicesRepository.getReporte(accion = accion, idUsuario = idUsuario, anio = anio, mes = mes)
        if (response?.status == "success") {
            val listaAlumnos = response.data?.alumnos ?: emptyList()
            val actividadesUsuario = response.data?.actividadesPorFecha ?: emptyList()
            val alumnosEntity = listaAlumnos.map {
                AlumnoEntity(
                    idUsuario = it.idUsuario,
                    nombre = it.nombre,
                )
            }
            val actividadesLista = actividadesUsuario.flatMap { actividadesPorFecha ->
                val anio = actividadesPorFecha.anio
                val mes = actividadesPorFecha.mes
                val fecha = actividadesPorFecha.fecha

                actividadesPorFecha.grupos.flatMap { grupo ->
                    val idTipoActividad = grupo.idTipoActividad
                    val tipoActvidad = grupo.tipoActividad

                    grupo.actividades.map { actividad ->
                        ActividadUsuarioEntity(
                            idActividad = actividad.idActividad,
                            nombreActividad = actividad.nombreActividad,
                            idUsuario = actividad.idUsuario,
                            realizado = actividad.realizado,
                            puntaje = actividad.puntaje,
                            idTipoActividad = idTipoActividad,
                            tipoActividad = tipoActvidad,
                            anio = anio,
                            mes = mes,
                            fecha = fecha
                        )
                    }
                }
            }
            roomRepository.clearAlumnos()
            roomRepository.insertAlumnos(alumnosEntity)
            roomRepository.clearActividadesUsuario()
            roomRepository.insertActividadesUsuario(actividadesLista)
            Log.i("Alumnos", roomRepository.getAlumnos().toString())
            Log.i("Actividades por Usuario", roomRepository.getAllActividadesUsuario().toString())
        }
        return response
    }

    suspend fun getUsuarioDB() = roomRepository.getUsuario()
    suspend fun getAlumnosDB() = roomRepository.getAlumnos()
    suspend fun getAsignacionesDB() = roomRepository.getAllActividadesUsuario()

}