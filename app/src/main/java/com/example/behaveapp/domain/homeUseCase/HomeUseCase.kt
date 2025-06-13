package com.example.behaveapp.domain.homeUseCase

import com.example.behaveapp.data.models.ActividadesResponse
import com.example.behaveapp.data.models.GenericResponse
import com.example.behaveapp.data.models.TipoActividadesResponse
import com.example.behaveapp.data.repository.ServicesRepository
import javax.inject.Inject

class HomeUseCase @Inject constructor(private val servicesRepository: ServicesRepository) {
    suspend fun getTipoActividades(): TipoActividadesResponse? =
        servicesRepository.getTipoActividades()

    suspend fun getActividades(idUsuario: Int): ActividadesResponse? =
        servicesRepository.getActividades(idUsuario = idUsuario)

    suspend fun saveActividad(
        tipoActividad: Int,
        tutor: Int,
        titulo: String,
        puntaje: Int
    ): GenericResponse? =
        servicesRepository.saveActivities(tipoActividad, tutor, titulo, puntaje)

    suspend fun editActividad(
        idActividad: Int,
        tipoActividad: Int,
        tutor: Int,
        titulo: String,
        puntaje: Int,
        eliminado: Int
    ): GenericResponse? =
        servicesRepository.editActivities(idActividad, tipoActividad, tutor, titulo, puntaje, eliminado)
}