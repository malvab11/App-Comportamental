package com.example.behaveapp.data.room.entity

import androidx.room.Entity

@Entity(
    tableName = "actividad_usuario",
    primaryKeys = ["idActividad", "idUsuario"]
)
data class ActividadUsuarioEntity(
    val idActividad: Int,
    val nombreActividad: String,
    val idUsuario: Int,
    val realizado: Boolean,
    val puntaje: Int,
    val idTipoActividad: Int,
    val tipoActividad: Int,
    val anio: String,
    val mes: String,
    val fecha: String
)
