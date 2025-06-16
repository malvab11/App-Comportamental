package com.example.behaveapp.data.models

import com.google.gson.annotations.SerializedName

data class TipoActividades(
    @SerializedName("id") val idTipoActividad: Int,
    @SerializedName("nombre") val descripcion: String,
    @SerializedName("descripcion") val detalle: String? = null
)

data class Actividades(
    val idActividad: Int? = 0,
    val tipoActividad: Int? = 0,
    val tutor: Int? = 0,
    val titulo: String,
    val descripcion: String? = "",
    val puntaje: Int = 0,
    val eliminado: Int = 0,
    val isSelected: Boolean = false
)

data class ActividadUsuario(
    val idActividad: Int,
    val nombreActividad: String,
    val idUsuario: Int,
    val realizado: Boolean,
    val puntaje: Int
)
