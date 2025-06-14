package com.example.behaveapp.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tipo_actividad")
data class TipoActividadEntity(
    @PrimaryKey val id: Int,
    val descripcion: String,
    val detalle: String?
)