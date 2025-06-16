package com.example.behaveapp.ui.viewModels.homeViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.behaveapp.data.room.entity.TipoActividadEntity
import com.example.behaveapp.domain.homeUseCase.ActividadesUseCase
import com.example.behaveapp.ui.data.home.CreateState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateViewModel @Inject constructor(private val createUseCase: ActividadesUseCase) : ViewModel() {
    private val _variables = MutableStateFlow(CreateState())
    val variables: StateFlow<CreateState> = _variables.asStateFlow()

    fun showDialog() {
        _variables.update { it.copy(isShownAlertDialog = !it.isShownAlertDialog) }
    }

    fun selectedValue(valor: TipoActividadEntity) {
        _variables.update { it.copy(tipoActividadSeleccionado = valor) }
    }

    fun clearValues(){
        _variables.update { it.copy(tituloActividad = "", puntaje = "", tipoActividadSeleccionado = null) }
    }

    fun getTipoActividades(){
        viewModelScope.launch {
            _variables.update { it.copy(tipoActividades = createUseCase.getTipoActividades()) }
        }
    }

    fun getActividadById(id: Int){
        viewModelScope.launch {
            val response = createUseCase.getActividadById(id)
            _variables.update { it.copy(actividad = response, tituloActividad = response.titulo, puntaje = response.puntaje.toString()) }
        }
    }

    fun getUser(){
        viewModelScope.launch {
            val response = createUseCase.getUsuario()
            _variables.update { it.copy(usuario = response) }
        }
    }

    // Función para habilitar el boton de crear actividad
    fun onValueChange(tituloActividad: String, puntaje: String) {
        _variables.update {
            it.copy(
                tituloActividad = tituloActividad,
                puntaje = puntaje,
                isEnabled = it.tipoActividadSeleccionado?.descripcion != null && it.tituloActividad.isNotBlank() && it.puntaje.isNotBlank()
            )
        }
    }

    fun saveActivities() {
        viewModelScope.launch {
            _variables.update { it.copy(isLoading = true) }
            val response =
                createUseCase.saveActividad(
                    tipoActividad = _variables.value.tipoActividadSeleccionado!!.id,
                    tutor = _variables.value.usuario?.idUsuario ?: 0,
                    titulo = _variables.value.tituloActividad,
                    puntaje = _variables.value.puntaje.toInt()
                )
            _variables.update { it.copy(createResponse = response, isLoading = false) }
        }
    }

    fun editActivities() {
        viewModelScope.launch {
            _variables.update { it.copy(isLoading = true) }
            val response = createUseCase.editActividad(
                idActividad = _variables.value.actividad?.idActividad ?: 0,
                tipoActividad = _variables.value.tipoActividadSeleccionado?.id ?: 0,
                tutor = _variables.value.actividad?.tutor ?: 0,
                titulo = _variables.value.tituloActividad,
                puntaje = _variables.value.puntaje.toInt(),
                eliminado = 0
            )
            _variables.update { it.copy(createResponse = response, isLoading = false) }
        }
    }
}