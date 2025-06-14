package com.example.behaveapp.ui.viewModels.homeViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.behaveapp.domain.homeUseCase.HomeUseCase
import com.example.behaveapp.ui.data.home.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCase: HomeUseCase
) : ViewModel() {

    private val _variables = MutableStateFlow(HomeState())
    val variables: StateFlow<HomeState> = _variables.asStateFlow()

    //Funcion para seleccionar pantallas
    fun selectScreen(idPantalla: Int) {
        _variables.update { it.copy(selectedScreen = idPantalla) }
    }


    //Funcion para mostrar el alertDialog
    fun showDialog(){
        _variables.update { it.copy(isShownAlertDialog = !it.isShownAlertDialog) }
    }


//
//    // Función para Seleccionar el tipo de actividad
//    fun onTipoActividadSeleccionado(tipo: TipoActividades) {
//        _tipoActividadSeleccionado.value = tipo
//    }
//
//    // Funcion para usar solo la actividad pasada
//    fun activityFilter(idActividad: Int) {
//        val actividad =
//            _actividades.value?.actividades?.filter { it.idActividad == idActividad } ?: emptyList()
//        val tipoActividadFiltrado =
//            _tipoActividades.value?.tipoActividades?.filter { it.id == actividad?.first()?.tipoActividad }
//                ?: emptyList()
//        if (actividad.isNotEmpty()) {
//            _tituloActividad.value = actividad.first().titulo
//            _puntaje.value = actividad.first().puntaje.toString()
//            _tipoActividadSeleccionado.value = tipoActividadFiltrado?.first()
//        }
//    }
//
//    // Función para actualizar el texto de búsqueda
//    fun actualizarBusqueda(query: String) {
//        _searchQuery.value = query
//    }
//
//    // Función para seleccionar pantalla
//    fun selectScreen(pantalla: Int) {
//        _variables.update { it.copy(selectedScreen = pantalla) }
//    }
//
//    fun getDataFull(idUsuario: Int) {
//        viewModelScope.launch {
//            _datosCargados.value = false
//
//            val tipos = homeUseCase.getTipoActividades()
//            val actividades = homeUseCase.getActividades(idUsuario)
//
//            if (tipos?.status == "success" && actividades?.status == "success") {
//                _tipoActividades.value = tipos
//                _actividades.value = actividades
//                _datosCargados.value = true
//            } else {
//                _mensaje.value =
//                    tipos?.message ?: actividades?.message ?: "Error al cargar los datos"
//            }
//
//        }
//    }
//
//    fun saveActivities(tituloActividad: String, puntaje: String) {
//        viewModelScope.launch {
//            _datosCargados.value = false
//
//            val response = homeUseCase.saveActividad(1, 1, tituloActividad, puntaje.toInt())
//
//            if (response?.status != "success") {
//                _mensaje.value = response?.message ?: "Error al guardar la actrividad"
//            }
//
//        }
//    }
//
//
}
