package com.example.behaveapp.data.repository

import com.example.behaveapp.data.models.ActividadesResponse
import com.example.behaveapp.data.models.LoginResponse
import com.example.behaveapp.data.models.RegisterResponse
import com.example.behaveapp.data.models.TipoActividadesResponse
import com.example.behaveapp.data.network.services.ApiServices
import javax.inject.Inject

class ServicesRepository @Inject constructor(private val apiServices: ApiServices) {

    //Login Validation
    suspend fun login(usuario: String, contrasena: String): LoginResponse? =
        apiServices.loginService(usuario = usuario, contrasena = contrasena)

    //Register User
    suspend fun registerTutor(
        nombre: String,
        contrasena: String,
        correo: String,
        celular: String
    ): RegisterResponse? = apiServices.regiterTutorService(
        nombre = nombre,
        contrasena = contrasena,
        correo = correo,
        celular = celular
    )
    suspend fun registerUser(nombre: String, codigoFamilia: String): RegisterResponse? =
        apiServices.registerUserService(
            nombre = nombre,
            codigoFamilia = codigoFamilia
        )

    //Activiades
    suspend fun getTipoActividades(): TipoActividadesResponse? = apiServices.getTipoActividades()
    suspend fun getActividades(idUsuario: Int) : ActividadesResponse? = apiServices.getActividades(idUsuario = idUsuario)
}