package com.example.behaveapp.data.room.mappers

import com.example.behaveapp.data.models.TipoActividades
import com.example.behaveapp.data.room.entity.TipoActividadEntity


fun TipoActividades.toEntity() = TipoActividadEntity(
    id = idTipoActividad,
    descripcion = descripcion,
    detalle = detalle
)

fun TipoActividadEntity.toModel() = TipoActividades(
    idTipoActividad = id,
    descripcion = descripcion,
    detalle = detalle
)
