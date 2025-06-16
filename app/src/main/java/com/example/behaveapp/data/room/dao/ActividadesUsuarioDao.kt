package com.example.behaveapp.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.behaveapp.data.room.entity.ActividadUsuarioEntity

@Dao
interface ActividadesUsuarioDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(actividad: ActividadUsuarioEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(actividades: List<ActividadUsuarioEntity>)

    @Query("SELECT * FROM actividad_usuario")
    suspend fun getAll(): List<ActividadUsuarioEntity>

    @Query("DELETE FROM actividad_usuario")
    suspend fun deleteAll()
}
