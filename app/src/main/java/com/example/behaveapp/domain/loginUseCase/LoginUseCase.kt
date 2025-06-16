package com.example.behaveapp.domain.loginUseCase

import android.util.Log
import com.example.behaveapp.data.models.GenericResponse
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

        if (response?.status == "success") {
            val usuarioEntity = UsuarioEntity(
                idUsuario = response.idUsuario!!,
                nombre = response.nombre ?: "",
                codigoFamilia = response.codigoFamilia,
                tipoUsuario = response.tipoUsuario!!
            )
            roomRepository.clearUsuario()
            roomRepository.insertUsuario(usuarioEntity)
            Log.i("UserDataBase",roomRepository.getUsuario().toString())
        }
        return response
    }

    suspend fun register(accion: String, datos: Any): LoginResponse? {
        val response = servicesRepository.registerTutor(accion = accion, datos = datos)

        if (response?.status == "success") {
            val usuarioEntity = UsuarioEntity(
                idUsuario = response.idUsuario!!,
                nombre = response.nombre ?: "",
                codigoFamilia = response.codigoFamilia ?: "",
                tipoUsuario = response.tipoUsuario!!
            )
            roomRepository.clearUsuario()
            roomRepository.insertUsuario(usuarioEntity)
            Log.i("UserDataBaseRegister",roomRepository.getUsuario().toString())
        }
        return response
    }

    suspend fun getUsuario(): UsuarioEntity? = roomRepository.getUsuario()
    suspend fun clearUser() = roomRepository.clearUsuario()
}