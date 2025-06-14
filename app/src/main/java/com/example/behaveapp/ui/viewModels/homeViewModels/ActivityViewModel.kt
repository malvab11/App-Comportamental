package com.example.behaveapp.ui.viewModels.homeViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.behaveapp.domain.homeUseCase.HomeUseCase
import com.example.behaveapp.ui.data.home.ActivityState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel @Inject constructor(private val activityUseCase: HomeUseCase) :
    ViewModel() {

    private val _variables = MutableStateFlow(ActivityState())
    val variables: StateFlow<ActivityState> = _variables.asStateFlow()

    fun showDialog() {
        _variables.update { it.copy(isShownAlertDialog = !it.isShownAlertDialog) }
    }

    //Funcion para busqueda
    fun search(query: String) {
        _variables.update { it.copy(searchQuery = query) }
    }

    fun getAll(idUsuario: Int){
        viewModelScope.launch {
            _variables.update { it.copy(isLoading = true) }
            val responseTipoActividades =
                activityUseCase.getTipoActividades(accion = "getTipoTarea", idUsuario = idUsuario)
            val responseActividadesList =
                activityUseCase.getActividadesList(accion = "getActividades", idUsuario = idUsuario)
            _variables.update { it.copy(tipoActividades = responseTipoActividades, actividades = responseActividadesList, isLoading = false) }
        }
    }

    //Seleccionar actividad para obtener su ID
    fun getActividadId(idActividad: Int){
        _variables.update { it.copy(idActividadSeleccionada = idActividad) }
    }

    //Funcion para traer valores
    fun getTipoActividades(idUsuario: Int) {
        viewModelScope.launch {
            _variables.update { it.copy(isLoading = true) }
            val response =
                activityUseCase.getTipoActividades(accion = "getTipoTarea", idUsuario = idUsuario)
            _variables.update { it.copy(tipoActividades = response, isLoading = false) }
        }
    }

    fun getActividadesList(idUsuario: Int) {
        viewModelScope.launch {
            _variables.update { it.copy(isLoading = true) }
            val response =
                activityUseCase.getActividadesList(accion = "getActividades", idUsuario = idUsuario)
            _variables.update { it.copy(actividades = response, isLoading = false) }
        }
    }

    fun deleteActivity(idActividad: Int) {
        viewModelScope.launch {
            _variables.update { it.copy(isLoading = true) }
            val response = activityUseCase.deleteActivity(
                accion = "eliminarActividad",
                idActividad = idActividad
            )
            _variables.update { it.copy(respuestaServicio = response, isLoading = false) }
        }
    }
}