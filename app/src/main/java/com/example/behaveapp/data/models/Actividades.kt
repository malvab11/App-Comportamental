package com.example.behaveapp.data.models

import com.google.gson.annotations.SerializedName

//Tipo Actividades
data class TipoActividadesRequest(
    val accion: String = "getTipoTarea"
)

data class TipoActividadesResponse(
    val status: String,
    val message: String?,
    @SerializedName("data") val tipoActividades: List<TipoActividades> = emptyList()
)

data class TipoActividades(
    val id: Int,
    @SerializedName("nombre") val descripcion: String,
    @SerializedName("descripcion") val detalle: String?
)

//Actividades
data class ActividadesRequest(
    val accion: String = "getActividades",
    val idUsuario: Int
)

data class ActividadesResponse(
    val status: String,
    val message: String?,
    @SerializedName("data") val actividades: List<Actividades> = emptyList()
)

data class Actividades(
    val idActividad: Int? = 0,
    val tipoActividad: Int? = 0,
    val tutor: Int? = 0,
    val titulo: String,
    val descripcion: String? = "",
    val puntaje: Int = 0,
    val eliminado: Int = 0
)

//Crear Actividades

data class ServiceActividadesRequest(
    val accion: String,
    val datos: Actividades
)

data class GenericResponse(
    val status: String,
    val message: String,
)