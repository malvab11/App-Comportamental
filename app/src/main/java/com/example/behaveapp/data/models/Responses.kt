package com.example.behaveapp.data.models

import com.google.gson.annotations.SerializedName

data class ApiResponse<T>(
    val status: String,
    val message: String? = "",
    val data: T? = null,
    @SerializedName("tipo_usuario") val tipoUsuario: Int? = 0
)

data class GenericResponse(
    val status: String,
    val message: String,
    val idUsuario: Int? = 0,
    @SerializedName("tipo_usuario") val tipoUsuario: Int? = 0
)

data class LoginResponse(
    val status: String,
    val message: String,
    val idUsuario: Int? = 0,
    @SerializedName("tipo_usuario") val tipoUsuario: Int? = 0,
    val nombre: String? = null,
    val codigoFamilia: String? = null
)
