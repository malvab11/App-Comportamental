package com.example.behaveapp.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.behaveapp.data.room.entity.UsuarioEntity

@Dao
interface UsuarioDao {
    @Query("SELECT * FROM usuario LIMIT 1")
    suspend fun getUsuario(): UsuarioEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(usuario: UsuarioEntity)

    @Query("DELETE FROM usuario")
    suspend fun clear()
}