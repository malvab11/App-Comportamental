package com.example.behaveapp.data.room.mappers

import com.example.behaveapp.data.models.Alumno
import com.example.behaveapp.data.models.Usuario
import com.example.behaveapp.data.room.entity.AlumnoEntity
import com.example.behaveapp.data.room.entity.UsuarioEntity

fun Usuario.toEntity() = UsuarioEntity(
    idUsuario = idUsuario,
    nombre = nombre,
    codigoFamilia = codigoFamilia,
    tipoUsuario = tipoUsuario,

)

fun UsuarioEntity.toModel() = Usuario(
    idUsuario = idUsuario,
    nombre = nombre,
    codigoFamilia = codigoFamilia
)

fun Alumno.toEntity() = AlumnoEntity(
    idUsuario = idUsuario,
    nombre = nombre
)

fun AlumnoEntity.toModel() = Alumno(
    idUsuario = idUsuario,
    nombre = nombre
)

