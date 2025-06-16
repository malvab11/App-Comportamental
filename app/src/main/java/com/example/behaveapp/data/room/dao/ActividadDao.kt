package com.example.behaveapp.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.behaveapp.data.room.entity.ActividadEntity

@Dao
interface ActividadDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(actividades: List<ActividadEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(actividad: ActividadEntity)

    @Query("SELECT * FROM actividad")
    suspend fun getAll(): List<ActividadEntity>

    @Query("SELECT * FROM actividad WHERE idActividad = :id")
    suspend fun getActivityById(id: Int): ActividadEntity

    @Query("DELETE FROM actividad")
    suspend fun deleteAll()
}