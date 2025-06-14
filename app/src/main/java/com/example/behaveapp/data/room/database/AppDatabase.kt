package com.example.behaveapp.data.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.behaveapp.data.room.dao.*
import com.example.behaveapp.data.room.entity.*

@Database(
    entities = [TipoActividadEntity::class, ActividadEntity::class, UsuarioEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tipoActividadDao(): TipoActividadDao
    abstract fun actividadDao(): ActividadDao
    abstract fun usuarioDao(): UsuarioDao
}