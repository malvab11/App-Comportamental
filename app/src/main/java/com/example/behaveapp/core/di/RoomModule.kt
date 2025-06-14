package com.example.behaveapp.core.di

import android.content.Context
import androidx.room.Room
import com.example.behaveapp.data.room.database.AppDatabase
import com.example.behaveapp.data.room.dao.ActividadDao
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
    fun provideActividadesDao(database: AppDatabase): ActividadDao {
        return database.actividadDao()
    }

    @Provides
    fun provideTipoActividadesDao(database: AppDatabase): TipoActividadDao {
        return database.tipoActividadDao()
    }

    @Provides
    fun provideUserDao(database: AppDatabase): UsuarioDao {
        return database.usuarioDao()
    }
}
