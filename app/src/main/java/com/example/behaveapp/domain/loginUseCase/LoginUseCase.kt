package com.example.behaveapp.domain.loginUseCase

import com.example.behaveapp.data.models.LoginResponse
import com.example.behaveapp.data.repository.ServicesRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val servicesRepository: ServicesRepository) {

    suspend fun login(usuario: String, contrasena: String): LoginResponse? = servicesRepository.login(usuario, contrasena)

}