package com.example.behaveapp.ui.data.home

import com.example.behaveapp.data.models.Actividades
import com.example.behaveapp.data.models.GenericResponse
import com.example.behaveapp.data.models.TipoActividades
import com.example.behaveapp.data.room.entity.ActividadEntity
import com.example.behaveapp.data.room.entity.TipoActividadEntity
import com.example.behaveapp.data.room.entity.UsuarioEntity

data class CreateState(
    //Variables
    val createResponse: GenericResponse? = null,
    //Inputs
    val tipoActividades: List<TipoActividadEntity>? = null,
    val actividad: ActividadEntity? = null,
    val tipoActividadSeleccionado: TipoActividadEntity? = null,
    val tituloActividad: String = "",
    val puntaje: String = "",

    //Dao
    val usuario: UsuarioEntity? = null,

    //Validadores
    val isLoading: Boolean = false,
    val isEnabled: Boolean = false,
    val isShownAlertDialog: Boolean = false
)
