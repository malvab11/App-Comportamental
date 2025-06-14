package com.example.behaveapp.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.behaveapp.data.models.ActividadesResponse
import com.example.behaveapp.data.models.TipoActividades
import com.example.behaveapp.data.models.TipoActividadesResponse
import com.example.behaveapp.domain.homeUseCase.HomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCase: HomeUseCase
) : ViewModel() {

    // UI State
    private val _selectedScreen = MutableLiveData(0)
    val selectedScreen: LiveData<Int> = _selectedScreen

    private val _datosCargados = MutableLiveData(false)
    val datosCargados: LiveData<Boolean> = _datosCargados

    private val _mensaje = MutableLiveData<String?>()
    val mensaje: LiveData<String?> = _mensaje

    private val _isEnabled = MutableLiveData<Boolean>()
    val isEnabled: LiveData<Boolean> = _isEnabled

    // Data
    private val _tipoActividades = MutableLiveData<TipoActividadesResponse?>()
    val tipoActividades: LiveData<TipoActividadesResponse?> = _tipoActividades

    private val _actividades = MutableLiveData<ActividadesResponse?>()
    val actividades: LiveData<ActividadesResponse?> = _actividades

    private val _tituloActividad = MutableLiveData<String>()
    val tituloActividad: LiveData<String> = _tituloActividad

    private val _tipoActividadSeleccionado = MutableLiveData<TipoActividades>()
    val tipoActividadSeleccionado: LiveData<TipoActividades> = _tipoActividadSeleccionado

    private val _puntaje = MutableLiveData<String>()
    val puntaje: LiveData<String> = _puntaje

    // Search
    private val _searchQuery = MutableLiveData("")
    val searchQuery: LiveData<String> = _searchQuery

    // Función para habilitar el boton de crear actividad
    fun onValueChange(tituloActividad: String, puntaje: String) {
        _tituloActividad.value = tituloActividad
        _puntaje.value = puntaje
        _isEnabled.value =
            _tipoActividadSeleccionado.value.descripcion.isNotBlank() && tituloActividad.isNotBlank() && puntaje.isNotBlank()
    }

    // Función para Seleccionar el tipo de actividad
    fun onTipoActividadSeleccionado(tipo: TipoActividades) {
        _tipoActividadSeleccionado.value = tipo
    }

    // Funcion para usar solo la actividad pasada
    fun activityFilter(idActividad: Int) {
        val actividad =
            _actividades.value?.actividades?.filter { it.idActividad == idActividad } ?: emptyList()
        val tipoActividadFiltrado =
            _tipoActividades.value?.tipoActividades?.filter { it.id == actividad?.first()?.tipoActividad }
                ?: emptyList()
        if (actividad.isNotEmpty()) {
            _tituloActividad.value = actividad.first().titulo
            _puntaje.value = actividad.first().puntaje.toString()
            _tipoActividadSeleccionado.value = tipoActividadFiltrado?.first()
        }
    }

    // Función para actualizar el texto de búsqueda
    fun actualizarBusqueda(query: String) {
        _searchQuery.value = query
    }

    // Función para seleccionar pantalla
    fun selectScreen(pantalla: Int) {
        _selectedScreen.value = pantalla
    }

    // Función para cargar datos desde el use case
    fun cargarDatos(idUsuario: Int) {
        viewModelScope.launch {
            _datosCargados.value = false

            val tipos = homeUseCase.getTipoActividades()
            val actividades = homeUseCase.getActividades(idUsuario)

            if (tipos?.status == "success" && actividades?.status == "success") {
                _tipoActividades.value = tipos
                _actividades.value = actividades
                _datosCargados.value = true
            } else {
                _mensaje.value =
                    tipos?.message ?: actividades?.message ?: "Error al cargar los datos"
            }

        }
    }

    fun saveActivities(tituloActividad: String, puntaje: String) {
        viewModelScope.launch {
            _datosCargados.value = false

            val response = homeUseCase.saveActividad(1, 1, tituloActividad, puntaje.toInt())

            if (response?.status != "success") {
                _mensaje.value = response?.message ?: "Error al guardar la actrividad"
            }

        }
    }

    fun editActivities(
        idActividad: Int,
        tituloActividad: String,
        puntaje: String,
        eliminado: Int = 0
    ) {
        viewModelScope.launch {
            _datosCargados.value = false

            val response = if (eliminado == 1) {
                val actividad =
                    _actividades.value?.actividades?.firstOrNull { it.idActividad == idActividad }
                if (actividad == null) {
                    _mensaje.value = "Actividad no encontrada"
                    return@launch
                }
                homeUseCase.editActividad(
                    idActividad = idActividad,
                    tipoActividad = actividad.tipoActividad!!,
                    tutor = actividad.tutor!!,
                    titulo = actividad.titulo!!,
                    puntaje = actividad.puntaje!!,
                    eliminado = 1
                )
            } else {
                homeUseCase.editActividad(
                    idActividad = idActividad,
                    tipoActividad = _tipoActividadSeleccionado.value.id,
                    tutor = 1,
                    titulo = tituloActividad,
                    puntaje = puntaje.toInt(),
                    eliminado = eliminado
                )
            }

            if (response?.status != "success") {
                _mensaje.value = response?.message ?: "Error al guardar la actividad"
            }
        }
    }
}
