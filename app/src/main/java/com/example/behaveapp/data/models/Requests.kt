package com.example.behaveapp.data.models

data class ActividadesRequest(
    val accion: String,
    val idUsuario: Int? = 0
)

data class CreateRequest(
    val accion: String,
    val idActividad: Int? = 0,
    val datos: Actividades? = null
)

data class LoginRequest(
    val accion: String,
    val usuario: String,
    val contrasena: String
)

data class RegisterTutorRequest(
    val accion: String,
    val correo: String,
    val celular: String,
    val nombre: String,
    val contrasena: String
)

data class RegisterUserRequest(
    val accion: String,
    val nombre: String,
    val codigoFamilia: String
)

data class ReporteRequest(
    val accion: String,
    val idUsuario: Int,
    val anio: String,
    val mes: String
)

