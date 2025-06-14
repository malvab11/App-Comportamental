package com.example.behaveapp.data.room.dao

import androidx.room.*
import com.example.behaveapp.data.room.entity.TipoActividadEntity

@Dao
interface TipoActividadDao {
    @Query("SELECT * FROM tipo_actividad")
    suspend fun getAll(): List<TipoActividadEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: TipoActividadEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(tipoActividad: List<TipoActividadEntity>)

    @Query("DELETE FROM tipo_actividad")
    suspend fun clear()
}