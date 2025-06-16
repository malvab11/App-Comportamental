package com.example.behaveapp.core.di

import android.content.Context
import androidx.room.Room
import com.example.behaveapp.data.room.database.AppDatabase
import com.example.behaveapp.data.room.dao.ActividadDao
import com.example.behaveapp.data.room.dao.ActividadesUsuarioDao
import com.example.behaveapp.data.room.dao.AlumnoDao
import com.example.behaveapp.data.room.dao.TipoActividadDao
import com.example.behaveapp.data.room.dao.UsuarioDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "app_database"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideActividadDao(database: AppDatabase): ActividadDao {
        return database.actividadDao()
    }

    @Provides
    fun provideTipoActividadDao(database: AppDatabase): TipoActividadDao {
        return database.tipoActividadDao()
    }

    @Provides
    fun provideUsuarioDao(database: AppDatabase): UsuarioDao {
        return database.usuarioDao()
    }

    @Provides
    fun provideAlumnoDao(database: AppDatabase): AlumnoDao {
        return database.alumnoDao()
    }

    @Provides
    fun provideActividadesUsuarioDao(database: AppDatabase): ActividadesUsuarioDao {
        return database.actividadesUsuarioDao()
    }
}
