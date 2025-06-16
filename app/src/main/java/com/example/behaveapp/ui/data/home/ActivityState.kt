package com.example.behaveapp.ui.data.home

import com.example.behaveapp.data.models.Actividades
import com.example.behaveapp.data.models.ApiResponse
import com.example.behaveapp.data.models.GenericResponse
import com.example.behaveapp.data.models.TipoActividades
import com.example.behaveapp.data.room.entity.ActividadEntity
import com.example.behaveapp.data.room.entity.TipoActividadEntity
import com.example.behaveapp.data.room.entity.UsuarioEntity

data class ActivityState(
    //Inputs
    val searchQuery: String = "",
    val idActividadSeleccionada: Int = 0,

    //Datos
    val tipoActividades: ApiResponse<List<TipoActividades>>? = null,
    val actividades: List<Actividades>? = null,
    val respuestaServicio: GenericResponse? = null,

    //DatosDao
    val usuarioDao: UsuarioEntity? = null,
    val tipoActividadesDao: List<TipoActividadEntity>? = null,
    val actividadesDao: List<ActividadEntity>? = null,
    val respuestaServicioDao: GenericResponse? = null,


    //Validadores
    val isLoading: Boolean = false,
    val isShownAlertDialog: Boolean = false,
    val isDataAvailable: Boolean = false
)