package com.example.behaveapp.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.behaveapp.data.models.Actividades
import com.example.behaveapp.data.room.entity.ActividadEntity

@Dao
interface ActividadDao {
    @Query("SELECT * FROM actividad")
    suspend fun getAll(): List<ActividadEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<ActividadEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(actividad: ActividadEntity)

    @Query("DELETE FROM actividad")
    suspend fun clear()
}