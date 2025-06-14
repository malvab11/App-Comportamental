package com.example.behaveapp.data.repository

import com.example.behaveapp.data.models.Actividades
import com.example.behaveapp.data.models.ActividadesResponse
import com.example.behaveapp.data.models.CreateResponse
import com.example.behaveapp.data.models.LoginResponse
import com.example.behaveapp.data.models.RegisterResponse
import com.example.behaveapp.data.models.TipoActividades
import com.example.behaveapp.data.network.services.ApiServices
import javax.inject.Inject

class ServicesRepository @Inject constructor(private val apiServices: ApiServices) {

    suspend fun login(accion: String, usuario: String, contrasena: String): LoginResponse? =
        apiServices.loginService(accion = accion, usuario = usuario, contrasena = contrasena)

    suspend fun registerTutor(accion: String, datos: Any): RegisterResponse? =
        apiServices.registerService(accion = accion, datos = datos)

    suspend fun getTipoActividades(accion: String, idUsuario: Int): ActividadesResponse<TipoActividades>? =
        apiServices.getTipoActividades(accion = accion, idUsuario = idUsuario)

    suspend fun getActividadesList(accion: String, idUsuario: Int): ActividadesResponse<Actividades>? = apiServices.getActividadesList(accion = accion, idUsuario = idUsuario)

    suspend fun saveActivities(
        tipoActividad: Int,
        tutor: Int,
        titulo: String,
        puntaje: Int
    ): CreateResponse? = apiServices.saveActivities(tipoActividad, tutor, titulo, puntaje)

    suspend fun editActivities(idActividad: Int, tipoActividad: Int, tutor: Int, titulo: String, puntaje: Int, eliminado: Int): CreateResponse? =
        apiServices.editActivities(idActividad, tipoActividad, tutor, titulo, puntaje, eliminado)

    suspend fun deleteActivity(accion: String, idActividad: Int): CreateResponse? = apiServices.deleteActividad(accion = accion, idActividad = idActividad)
}