package com.example.behaveapp.data.room.mappers

import com.example.behaveapp.data.models.LoginResponse
import com.example.behaveapp.data.room.entity.UsuarioEntity

fun LoginResponse.toEntity() = UsuarioEntity(
    idUsuario = this.idUsuario ?: 0,
    tipoUsuario = this.tipoUsuario ?: 0
)

