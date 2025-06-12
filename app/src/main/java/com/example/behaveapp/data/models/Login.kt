package com.example.behaveapp.data.models

data class LoginRequest(
    val accion: String = "login",
    val usuario: String,
    val contrasena: String
)

data class LoginResponse(
    val status: String,
    val message: String,
    val nombre: String? = "",
    val tipo_usuario: Int? = 0,
    val codigoFamilia: String? = ""
)

