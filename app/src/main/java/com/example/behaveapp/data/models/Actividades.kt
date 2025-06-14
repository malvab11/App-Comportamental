package com.example.behaveapp.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ActividadesRequest(
    val accion: String,
    val idUsuario: Int? = 0,
)

data class ActividadesResponse<T>(
    val status: String,
    val message: String,
    val data: List<T> = emptyList()
)

@Parcelize
data class TipoActividades(
    val id: Int,
    @SerializedName("nombre") val descripcion: String,
    @SerializedName("descripcion") val detalle: String?
) : Parcelable

@Parcelize
data class Actividades(
    val idActividad: Int? = 0,
    val tipoActividad: Int? = 0,
    val tutor: Int? = 0,
    val titulo: String,
    val descripcion: String? = "",
    val puntaje: Int = 0,
    val eliminado: Int = 0,
    val isSelected: Boolean? = false
) : Parcelable
