package com.example.behaveapp.domain.registerUseCases

import com.example.behaveapp.data.models.RegisterResponse
import com.example.behaveapp.data.repository.ServicesRepository
import javax.inject.Inject

class RegisterUseCases @Inject constructor(private val servicesRepository: ServicesRepository) {

    suspend fun registerTutor(nombre: String, contrasena: String, correo: String, celular: String):RegisterResponse? = servicesRepository.registerTutor(nombre,contrasena,correo,celular)
    suspend fun registerUser(nombre: String, codigoFamilia: String):RegisterResponse? = servicesRepository.registerUser(nombre,codigoFamilia)
}