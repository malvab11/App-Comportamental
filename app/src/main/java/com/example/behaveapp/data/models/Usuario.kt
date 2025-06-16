package com.example.behaveapp.data.models

data class Usuario(
    val idUsuario: Int,
    val nombre: String? = null,
    val codigoFamilia: String? = null,
    val tipoUsuario: Int? = 0
)

data class Alumno(
    val idUsuario: Int,
    val nombre: String
)
