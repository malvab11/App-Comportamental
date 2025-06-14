package com.example.behaveapp.domain.registerUseCases

import com.example.behaveapp.data.models.RegisterResponse
import com.example.behaveapp.data.repository.ServicesRepository
import javax.inject.Inject

class RegisterUseCases @Inject constructor(private val servicesRepository: ServicesRepository) {
    suspend fun register(accion: String, datos: Any):RegisterResponse? = servicesRepository.registerTutor(accion = accion, datos = datos)
}