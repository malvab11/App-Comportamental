package com.example.behaveapp.data.room.dao

import androidx.room.*
import com.example.behaveapp.data.room.entity.TipoActividadEntity

@Dao
interface TipoActividadDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(tipos: List<TipoActividadEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tipo: TipoActividadEntity)

    @Query("SELECT * FROM tipo_actividad")
    suspend fun getAll(): List<TipoActividadEntity>

    @Query("DELETE FROM tipo_actividad")
    suspend fun deleteAll()
}