package com.example.behaveapp.data.repository

import com.example.behaveapp.data.models.LoginRequest
import com.example.behaveapp.data.models.LoginResponse
import com.example.behaveapp.data.network.services.ApiServices
import javax.inject.Inject

class ServicesRepository @Inject constructor(private val apiServices: ApiServices) {

    suspend fun login(usuario: String, contrasena: String): LoginResponse? = apiServices.loginService(usuario = usuario, contrasena = contrasena)

}