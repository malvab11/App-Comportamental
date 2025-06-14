package com.example.behaveapp.domain.loginUseCase

import com.example.behaveapp.data.models.LoginResponse
import com.example.behaveapp.data.repository.RoomRepository
import com.example.behaveapp.data.repository.ServicesRepository
import com.example.behaveapp.data.room.entity.UsuarioEntity
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val servicesRepository: ServicesRepository,
    private val roomRepository: RoomRepository
) {
    suspend fun login(accion: String, usuario: String, contrasena: String): LoginResponse? {
        val response = servicesRepository.login(accion = accion, usuario = usuario, contrasena = contrasena)

        if(response!!.status == "success"){
            val usuarioEntity =  UsuarioEntity(idUsuario = response.idUsuario!!, tipoUsuario = response.tipoUsuario!!)
            roomRepository.clearUsuario()
            roomRepository.insertUsuario(usuarioEntity)
        }
        return response
    }

    suspend fun getUsuario(): UsuarioEntity? = roomRepository.getUsuario()
}