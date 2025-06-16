package com.example.behaveapp.data.room.mappers

import com.example.behaveapp.data.models.ActividadUsuario
import com.example.behaveapp.data.models.Actividades
import com.example.behaveapp.data.room.entity.ActividadEntity
import com.example.behaveapp.data.room.entity.ActividadUsuarioEntity


fun Actividades.toEntity() = ActividadEntity(
    idActividad = idActividad ?: 0,
    tipoActividad = tipoActividad,
    tutor = tutor,
    titulo = titulo,
    descripcion = descripcion,
    puntaje = puntaje,
    eliminado = eliminado,
    isSelected = isSelected
)

fun ActividadEntity.toModel() = Actividades(
    idActividad = idActividad,
    tipoActividad = tipoActividad,
    tutor = tutor,
    titulo = titulo,
    descripcion = descripcion,
    puntaje = puntaje,
    eliminado = eliminado,
    isSelected = isSelected
)

//fun ActividadUsuario.toEntity() = ActividadUsuarioEntity(
//    idActividad = idActividad,
//    nombreActividad = nombreActividad,
//    idUsuario = idUsuario,
//    realizado = realizado,
//    puntaje = puntaje
//)
//
//fun ActividadUsuarioEntity.toModel() = ActividadUsuario(
//    idActividad = idActividad,
//    nombreActividad = nombreActividad,
//    idUsuario = idUsuario,
//    realizado = realizado,
//    puntaje = puntaje
//)


