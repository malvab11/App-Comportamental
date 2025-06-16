package com.example.behaveapp.data.repository

import com.example.behaveapp.data.room.dao.ActividadDao
import com.example.behaveapp.data.room.dao.ActividadesUsuarioDao
import com.example.behaveapp.data.room.dao.AlumnoDao
import com.example.behaveapp.data.room.dao.TipoActividadDao
import com.example.behaveapp.data.room.dao.UsuarioDao
import com.example.behaveapp.data.room.entity.ActividadEntity
import com.example.behaveapp.data.room.entity.ActividadUsuarioEntity
import com.example.behaveapp.data.room.entity.AlumnoEntity
import com.example.behaveapp.data.room.entity.TipoActividadEntity
import com.example.behaveapp.data.room.entity.UsuarioEntity
import javax.inject.Inject

class RoomRepository @Inject constructor(
    private val usuarioDao: UsuarioDao,
    private val actividadDao: ActividadDao,
    private val tipoActividadDao: TipoActividadDao,
    private val actividadesUsuarioDao: ActividadesUsuarioDao,
    private val alumnoDao: AlumnoDao
) {

    // --- Usuario ---
    suspend fun insertUsuario(usuario: UsuarioEntity) = usuarioDao.insert(usuario)
    suspend fun getUsuario(): UsuarioEntity? = usuarioDao.getUsuario()
    suspend fun clearUsuario() = usuarioDao.deleteAll()

    // --- Alumnos ---
    suspend fun insertAlumno(alumno: AlumnoEntity) = alumnoDao.insert(alumno)
    suspend fun insertAlumnos(alumnos: List<AlumnoEntity>) = alumnoDao.insertAll(alumnos)
    suspend fun getAlumnos(): List<AlumnoEntity> = alumnoDao.getAll()
    suspend fun clearAlumnos() = alumnoDao.deleteAll()

    // --- Actividades ---
    suspend fun insertActividad(actividad: ActividadEntity) = actividadDao.insert(actividad)
    suspend fun insertActividades(list: List<ActividadEntity>) = actividadDao.insertAll(list)
    suspend fun getAllActividades(): List<ActividadEntity> = actividadDao.getAll()
    suspend fun getActividadById(id: Int): ActividadEntity = actividadDao.getActivityById(id)
    suspend fun clearActividades() = actividadDao.deleteAll()

    // --- Actividades por usuario ---
    suspend fun insertActividadUsuario(actividad: ActividadUsuarioEntity) =
        actividadesUsuarioDao.insert(actividad)

    suspend fun insertActividadesUsuario(list: List<ActividadUsuarioEntity>) =
        actividadesUsuarioDao.insertAll(list)

    suspend fun getAllActividadesUsuario(): List<ActividadUsuarioEntity> =
        actividadesUsuarioDao.getAll()

    suspend fun clearActividadesUsuario() = actividadesUsuarioDao.deleteAll()

    // --- Tipos de Actividad ---
    suspend fun insertTipo(tipo: TipoActividadEntity) = tipoActividadDao.insert(tipo)
    suspend fun insertTipos(list: List<TipoActividadEntity>) = tipoActividadDao.insertAll(list)
    suspend fun getAllTipos(): List<TipoActividadEntity> = tipoActividadDao.getAll()
    suspend fun clearTipos() = tipoActividadDao.deleteAll()

    // --- Limpieza total ---
    suspend fun clearAll() {
        clearUsuario()
        clearAlumnos()
        clearActividades()
        clearActividadesUsuario()
        clearTipos()
    }
}
