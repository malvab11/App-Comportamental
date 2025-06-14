package com.example.behaveapp.data.repository

import com.example.behaveapp.data.models.ActividadesResponse
import com.example.behaveapp.data.models.GenericResponse
import com.example.behaveapp.data.models.LoginResponse
import com.example.behaveapp.data.models.RegisterResponse
import com.example.behaveapp.data.models.TipoActividadesResponse
import com.example.behaveapp.data.network.services.ApiServices
import javax.inject.Inject

class ServicesRepository @Inject constructor(private val apiServices: ApiServices) {

    //Login Validation
    suspend fun login(accion: String, usuario: String, contrasena: String): LoginResponse? = apiServices.loginService(accion = accion, usuario = usuario, contrasena = contrasena)

    //Register
    suspend fun registerTutor(accion: String, datos: Any): RegisterResponse? = apiServices.registerService(accion = accion, datos = datos)

    //Activiades
    suspend fun getTipoActividades(): TipoActividadesResponse? = apiServices.getTipoActividades()
    suspend fun getActividades(idUsuario: Int): ActividadesResponse? =
        apiServices.getActividades(idUsuario = idUsuario)

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
}