package com.example.behaveapp.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuario")
data class UsuarioEntity(
    @PrimaryKey val idUsuario: Int,
    val tipoUsuario: Int
)