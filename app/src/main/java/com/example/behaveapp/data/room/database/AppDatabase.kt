package com.example.behaveapp.data.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
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

@Database(
    entities = [TipoActividadEntity::class, ActividadEntity::class, UsuarioEntity::class, AlumnoEntity::class, ActividadUsuarioEntity::class],
    version = 11,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun usuarioDao(): UsuarioDao
    abstract fun tipoActividadDao(): TipoActividadDao
    abstract fun actividadDao(): ActividadDao
    abstract fun actividadesUsuarioDao(): ActividadesUsuarioDao
    abstract fun alumnoDao(): AlumnoDao
}