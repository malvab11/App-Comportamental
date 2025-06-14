package com.example.behaveapp.ui.data.home

import com.example.behaveapp.data.models.Actividades
import com.example.behaveapp.data.models.ActividadesResponse
import com.example.behaveapp.data.models.CreateResponse
import com.example.behaveapp.data.models.TipoActividades

data class ActivityState(
    //Inputs
    val searchQuery: String = "",
    val idActividadSeleccionada: Int = 0,

    //Datos
    val tipoActividades: ActividadesResponse<TipoActividades>? = null,
    val actividades: ActividadesResponse<Actividades>? = null,
    val respuestaServicio: CreateResponse? = null,


    //Validadores
    val isLoading: Boolean = false,
    val isShownAlertDialog: Boolean = false
)