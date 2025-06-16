package com.example.behaveapp.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.behaveapp.data.room.entity.AlumnoEntity

@Dao
interface AlumnoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(alumno: AlumnoEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(alumnos: List<AlumnoEntity>)

    @Query("SELECT * FROM alumnos")
    suspend fun getAll(): List<AlumnoEntity>

    @Query("DELETE FROM alumnos")
    suspend fun deleteAll()
}