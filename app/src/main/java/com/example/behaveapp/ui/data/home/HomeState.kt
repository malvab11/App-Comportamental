package com.example.behaveapp.ui.data.home

import com.example.behaveapp.data.models.Actividades
import com.example.behaveapp.data.models.ActividadesResponse
import com.example.behaveapp.data.models.TipoActividades

data class HomeState (
    val selectedScreen: Int = 0,
    val isEnabled: Boolean = false,
    val tipoActividades: ActividadesResponse<TipoActividades>? = null,
    val actividades: ActividadesResponse<Actividades>? = null,
    val tipoActividadSeleccionado: TipoActividades? = null,
    val tituloActividad: String = "",
    val puntaje: String = "",
    val searchQuery: String = "",
    val isLoading: Boolean = false,
    val isShownAlertDialog: Boolean = false
)
