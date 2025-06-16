package com.example.behaveapp.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "alumnos")
data class AlumnoEntity(
    @PrimaryKey val idUsuario: Int,
    val nombre: String
)
