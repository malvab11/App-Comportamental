package com.example.behaveapp.data.models

data class ReporteData(
    val usuario: Usuario,
    val alumnos: List<Alumno> = emptyList(),
    val actividadesPorFecha: List<ActividadPorFecha> = emptyList()
)

data class ActividadPorFecha(
    val anio: String,
    val mes: String,
    val fecha: String,
    val grupos: List<GrupoActividad> = emptyList()
)

data class GrupoActividad(
    val idTipoActividad: Int,
    val tipoActividad: Int,
    val actividades: List<ActividadUsuario> = emptyList()
)

