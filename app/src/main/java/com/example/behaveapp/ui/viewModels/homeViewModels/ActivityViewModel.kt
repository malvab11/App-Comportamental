package com.example.behaveapp.ui.viewModels.homeViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.behaveapp.data.models.Actividades
import com.example.behaveapp.domain.homeUseCase.ActividadesUseCase
import com.example.behaveapp.domain.homeUseCase.HomeUseCase
import com.example.behaveapp.domain.homeUseCase.ReporteUseCase
import com.example.behaveapp.ui.data.home.ActivityState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel @Inject constructor(
    private val activityUseCase: ActividadesUseCase
) : ViewModel() {

    private val _variables = MutableStateFlow(ActivityState())
    val variables: StateFlow<ActivityState> = _variables.asStateFlow()

    fun getActividadesDB() {
        if (_variables.value.actividadesDao != null) return
        viewModelScope.launch {
            val response = activityUseCase.getActividades()
            _variables.update { it.copy(actividadesDao = response) }
        }
    }

    fun getTipoActividadDB() {
        if (_variables.value.tipoActividadesDao != null) return
        viewModelScope.launch {
            val response = activityUseCase.getTipoActividades()
            _variables.update { it.copy(tipoActividadesDao = response) }
        }
    }

    fun getUserDB() {
        if (_variables.value.usuarioDao != null) return
        viewModelScope.launch {
            val response = activityUseCase.getUsuario()
            _variables.update { it.copy(usuarioDao = response) }
        }
    }

    // Traer ID de actividad seleccionada
    fun getActividadId(idActividad: Int) {
        _variables.update { it.copy(idActividadSeleccionada = idActividad) }
    }

    // Mostrar/ocultar diálogo
    fun showDialog() {
        _variables.update { it.copy(isShownAlertDialog = !_variables.value.isShownAlertDialog) }
    }

    // Buscar actividad
    fun search(query: String) {
        _variables.update { it.copy(searchQuery = query) }
    }

    // Llamado al servicio para obtener tipos de actividad
    fun getTipoActividades(idUsuario: Int) {
        viewModelScope.launch {
            _variables.update { it.copy(isLoading = true) }
            val response = activityUseCase.getTipoActividades(
                accion = "getTipoTarea",
                idUsuario = idUsuario
            )
            _variables.update { it.copy(tipoActividades = response, isLoading = false) }
        }
    }

    // Llamado al servicio para obtener lista de actividades
    fun getActividadesList() {
        viewModelScope.launch {
            val idUsuario = _variables.value.usuarioDao?.idUsuario ?: 0
            val response = activityUseCase.getActividadesList(
                accion = "getActividades",
                idUsuario = idUsuario
            )
            val actividades = response?.data ?: emptyList()
            _variables.update {
                it.copy(
                    actividades = actividades.map { actividad ->
                        Actividades(
                            idActividad = actividad.idActividad,
                            tipoActividad = actividad.tipoActividad,
                            tutor = actividad.tutor,
                            titulo = actividad.titulo,
                            descripcion = actividad.descripcion,
                            puntaje = actividad.puntaje,
                            eliminado = actividad.eliminado,
                            isSelected = actividad.isSelected,
                        )
                    }
                )
            }
        }
    }

    // 🔁 NUEVA FUNCIÓN ORDENADA
    fun deleteAndRefresh(idActividad: Int) {
        viewModelScope.launch {
            _variables.update { it.copy(isLoading = true) }

            // Eliminar actividad
            val response = activityUseCase.deleteActivity(
                accion = "eliminarActividad",
                idActividad = idActividad
            )
            _variables.update { it.copy(respuestaServicio = response) }

            if (response?.status == "success") {
                // Obtener actividades del servicio
                val idUsuario = _variables.value.usuarioDao?.idUsuario ?: 0
                val responseList = activityUseCase.getActividadesList(
                    accion = "getActividades",
                    idUsuario = idUsuario
                )
                val actividades = responseList?.data ?: emptyList()
                _variables.update {
                    it.copy(
                        actividades = actividades.map { actividad ->
                            Actividades(
                                idActividad = actividad.idActividad,
                                tipoActividad = actividad.tipoActividad,
                                tutor = actividad.tutor,
                                titulo = actividad.titulo,
                                descripcion = actividad.descripcion,
                                puntaje = actividad.puntaje,
                                eliminado = actividad.eliminado,
                                isSelected = actividad.isSelected,
                            )
                        }
                    )
                }

                // Actualizar Room
                val actividadesDB = activityUseCase.getActividades()
                _variables.update { it.copy(actividadesDao = actividadesDB) }
            }

            _variables.update { it.copy(isLoading = false) }
        }
    }
}