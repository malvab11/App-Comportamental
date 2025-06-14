package com.example.behaveapp.data.room.mappers

import com.example.behaveapp.data.models.TipoActividades
import com.example.behaveapp.data.room.entity.TipoActividadEntity


fun TipoActividades.toEntity(): TipoActividadEntity = TipoActividadEntity(
    id = id,
    descripcion = descripcion,
    detalle = detalle.orEmpty()
)

fun TipoActividadEntity.toModel(): TipoActividades = TipoActividades(
    id = id,
    descripcion = descripcion,
    detalle = detalle
)
