package com.example.behaveapp.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "actividad")
data class ActividadEntity(
    @PrimaryKey val idActividad: Int,
    val tipoActividad: Int?,
    val tutor: Int?,
    val titulo: String,
    val descripcion: String?,
    val puntaje: Int,
    val eliminado: Int,
    val isSelected: Boolean
)