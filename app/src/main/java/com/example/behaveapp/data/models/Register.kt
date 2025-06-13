package com.example.behaveapp.data.models

data class RegisterTutorRequest(
    val accion: String = "registroTutor",
    val correo : String = "",
    val celular : String = "",
    val contrasena : String,
    val nombre : String,
)

data class RegisterUserRequest(
    val accion: String = "registroAlumno",
    val nombre : String,
    val codigoFamilia : String
)

data class RegisterResponse(
    val status : String,
    val message : String
)