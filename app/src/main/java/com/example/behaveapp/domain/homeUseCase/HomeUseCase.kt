package com.example.behaveapp.domain.homeUseCase

import com.example.behaveapp.data.models.ActividadesResponse
import com.example.behaveapp.data.models.LoginResponse
import com.example.behaveapp.data.models.TipoActividadesResponse
import com.example.behaveapp.data.repository.ServicesRepository
import javax.inject.Inject

class HomeUseCase @Inject constructor(private val servicesRepository: ServicesRepository) {
        suspend fun getTipoActividades(): TipoActividadesResponse? = servicesRepository.getTipoActividades()
        suspend fun getActividades(idUsuario: Int): ActividadesResponse? = servicesRepository.getActividades(idUsuario = idUsuario)
}