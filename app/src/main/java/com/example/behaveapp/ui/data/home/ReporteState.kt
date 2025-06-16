package com.example.behaveapp.ui.data.home

import com.example.behaveapp.data.room.entity.AlumnoEntity
import com.example.behaveapp.data.room.entity.UsuarioEntity

data class ReporteState (
    val usuario: UsuarioEntity? = null,
    val alumnos: List<AlumnoEntity>? = null
)