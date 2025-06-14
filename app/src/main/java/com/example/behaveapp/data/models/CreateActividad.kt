package com.example.behaveapp.data.models

data class CreateRequest(
    val accion: String,
    val idActividad: Int? = 0,
    val datos: Actividades? = null
)

data class CreateResponse(
    val status: String,
    val message: String,
)