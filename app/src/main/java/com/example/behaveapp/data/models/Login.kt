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
    val nombre: String? = null,
    @SerializedName("tipo_usuario") val tipoUsuario: Int? = null,
    val codigoFamilia: String? = null
)
