package com.example.behaveapp.ui.data.init

import com.example.behaveapp.data.models.Actividades
import com.example.behaveapp.data.models.ApiResponse
import com.example.behaveapp.data.models.ReporteData
import com.example.behaveapp.data.models.TipoActividades
import com.example.behaveapp.data.room.entity.UsuarioEntity

data class LoadingState(
    val reportData: ApiResponse<ReporteData>? = null,
    val loadingData: UsuarioEntity? = null,

    val tipoActividades: List<TipoActividades>? = null,
    val actividades: List<Actividades>? = null,

    val isLoading: Boolean = true,
    val isLogged: Boolean? = null,
    val isDataCompleted: Boolean = false
)