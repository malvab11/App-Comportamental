package com.example.behaveapp.data.repository

import com.example.behaveapp.data.room.dao.ActividadDao
import com.example.behaveapp.data.room.dao.TipoActividadDao
import com.example.behaveapp.data.room.dao.UsuarioDao
import com.example.behaveapp.data.room.entity.ActividadEntity
import com.example.behaveapp.data.room.entity.TipoActividadEntity
import com.example.behaveapp.data.room.entity.UsuarioEntity
import javax.inject.Inject

class RoomRepository @Inject constructor(
    private val usuarioDao: UsuarioDao,
    private val actividadDao: ActividadDao,
    private val tipoActividadDao: TipoActividadDao
) {

    // Usuario
    suspend fun insertUsuario(usuario: UsuarioEntity) = usuarioDao.insert(usuario)
    suspend fun getUsuario(): UsuarioEntity? = usuarioDao.getUsuario()
    suspend fun clearUsuario() = usuarioDao.clear()

    // Actividades
    suspend fun insertActividad(actividad: ActividadEntity) = actividadDao.insert(actividad)
    suspend fun insertActividades(list: List<ActividadEntity>) = actividadDao.insertAll(list)
    suspend fun getAllActividades(): List<ActividadEntity> = actividadDao.getAll()
    suspend fun clearActividades() = actividadDao.clear()

    // Tipos de Actividad
    suspend fun insertTipo(tipo: TipoActividadEntity) = tipoActividadDao.insert(tipo)
    suspend fun insertarTipos(list: List<TipoActividadEntity>) = tipoActividadDao.insertAll(list)
    suspend fun getAllTipos(): List<TipoActividadEntity> = tipoActividadDao.getAll()
    suspend fun clearTipos() = tipoActividadDao.clear()
}
