package com.example.behaveapp.data.models

import com.google.gson.annotations.SerializedName

data class TipoActividadesRequest(
    val accion: String = "getTipoTarea"
)

data class ActividadesRequest(
    val accion: String = "getActividades",
    val idUsuario: Int
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

data class ActividadesResponse(
    val status: String,
    val message: String?,
    @SerializedName("data") val actividades: List<Actividades> = emptyList()
)

data class Actividades(
    val tipoActividad: Int,
    val titulo: String,
    val descripcion: String?,
    val puntaje: Int
)
