package com.example.behaveapp.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuarios")
data class UsuarioEntity(
    @PrimaryKey(autoGenerate = false) val idUsuario: Int,
    val nombre: String?,
    val codigoFamilia: String?,
    val tipoUsuario: Int?
)
