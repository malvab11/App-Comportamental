package com.example.behaveapp.data.models

data class RegisterResponse(
    val status : String,
    val message : String,
    val idUsuario: Int
)

data class RegisterTutorRequest(
    val accion: String,
    val correo : String,
    val celular : String,
    val nombre : String,
    val contrasena : String,
)

data class RegisterUserRequest(
    val accion: String,
    val nombre : String,
    val codigoFamilia : String
)