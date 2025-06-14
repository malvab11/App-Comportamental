package com.example.behaveapp.ui.data.home

import com.example.behaveapp.data.models.Actividades
import com.example.behaveapp.data.models.CreateResponse
import com.example.behaveapp.data.models.TipoActividades

data class CreateState(
    //Variables
    val createResponse: CreateResponse? = null,
    //Inputs
    val tipoActividades: List<TipoActividades>? = null,
    val actividad: Actividades? = null,
    val tipoActividadSeleccionado: TipoActividades? = null,
    val tituloActividad: String = "",
    val puntaje: String = "",
    //Validadores
    val isLoading: Boolean = false,
    val isEnabled: Boolean = false,
    val isShownAlertDialog: Boolean = false
)
