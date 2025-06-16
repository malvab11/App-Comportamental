package com.example.behaveapp.data.repository

import com.example.behaveapp.data.models.Actividades
import com.example.behaveapp.data.models.ApiResponse
import com.example.behaveapp.data.models.GenericResponse
import com.example.behaveapp.data.models.LoginResponse
import com.example.behaveapp.data.models.ReporteData
import com.example.behaveapp.data.models.TipoActividades
import com.example.behaveapp.data.network.services.ApiServices
import javax.inject.Inject

class ServicesRepository @Inject constructor(private val apiServices: ApiServices) {

    suspend fun login(accion: String, usuario: String, contrasena: String): LoginResponse? =
        apiServices.loginService(accion = accion, usuario = usuario, contrasena = contrasena)

    suspend fun registerTutor(accion: String, datos: Any): LoginResponse? =
        apiServices.registerService(accion = accion, datos = datos)

    suspend fun getTipoActividades(
        accion: String,
        idUsuario: Int
    ): ApiResponse<List<TipoActividades>>? =
        apiServices.getTipoActividades(accion = accion, idUsuario = idUsuario)

    suspend fun getActividadesList(
        accion: String,
        idUsuario: Int
    ): ApiResponse<List<Actividades>>? =
        apiServices.getActividadesList(accion = accion, idUsuario = idUsuario)

    suspend fun saveActivities(
        tipoActividad: Int,
        tutor: Int,
        titulo: String,
        puntaje: Int
    ): GenericResponse? = apiServices.saveActivities(tipoActividad, tutor, titulo, puntaje)

    suspend fun editActivities(
        idActividad: Int,
        tipoActividad: Int,
        tutor: Int,
        titulo: String,
        puntaje: Int,
        eliminado: Int
    ): GenericResponse? =
        apiServices.editActivities(idActividad, tipoActividad, tutor, titulo, puntaje, eliminado)

    suspend fun deleteActivity(accion: String, idActividad: Int): GenericResponse? =
        apiServices.deleteActividad(accion = accion, idActividad = idActividad)

    suspend fun getReporte(
        accion: String,
        idUsuario: Int,
        anio: String,
        mes: String
    ): ApiResponse<ReporteData>? =
        apiServices.getReporte(accion = accion, idUsuario = idUsuario, anio = anio, mes = mes)
}