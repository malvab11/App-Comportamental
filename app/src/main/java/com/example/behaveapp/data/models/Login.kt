package com.example.behaveapp.data.models

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    val accion: String,
    val usuario: String,
    val contrasena: String
)

data class LoginResponse(
    val status: String,
    val message: String,
    val idUsuario: Int? = 0,
    @SerializedName("tipo_usuario") val tipoUsuario: Int? = 0
)
