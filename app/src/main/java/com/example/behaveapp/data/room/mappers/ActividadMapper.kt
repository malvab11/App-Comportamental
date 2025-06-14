package com.example.behaveapp.data.room.mappers

import com.example.behaveapp.data.models.Actividades
import com.example.behaveapp.data.room.entity.ActividadEntity


fun Actividades.toEntity(): ActividadEntity = ActividadEntity(
    idActividad = idActividad ?: 0,
    tipoActividad = tipoActividad ?: 0,
    tutor = tutor ?: 0,
    titulo = titulo,
    descripcion = descripcion.orEmpty(),
    puntaje = puntaje,
    eliminado = eliminado,
    isSelected = isSelected ?: false
)

fun ActividadEntity.toModel(): Actividades = Actividades(
    idActividad = idActividad,
    tipoActividad = tipoActividad,
    tutor = tutor,
    titulo = titulo,
    descripcion = descripcion,
    puntaje = puntaje,
    eliminado = eliminado,
    isSelected = isSelected
)

