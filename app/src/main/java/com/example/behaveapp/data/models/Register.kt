package com.example.behaveapp.data.models

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    val status : String,
    val message : String,
    val idUsuario: Int? = 0,
    @SerializedName("tipo_usuario") val tipoUsuario: Int? = 0
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