package com.example.behaveapp.ui.screens.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.behaveapp.data.models.Actividades
import com.example.behaveapp.data.models.ActividadesResponse
import com.example.behaveapp.data.models.TipoActividadesResponse
import com.example.behaveapp.domain.homeUseCase.HomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeUseCase: HomeUseCase) : ViewModel() {

    private val _selectedScreen = MutableLiveData<Int>(0)
    val selectedScreen: LiveData<Int> = _selectedScreen

    private val _tipoActividades = MutableLiveData<TipoActividadesResponse?>()
    val tipoActividades: LiveData<TipoActividadesResponse?> = _tipoActividades

    private val _actividades = MutableLiveData<ActividadesResponse?>()
    val actividades: LiveData<ActividadesResponse?> = _actividades

    private val _searchQuery = MutableLiveData<String>("")
    val searchQuery: LiveData<String> = _searchQuery

    private val _mensajeError = MutableLiveData<String?>()
    val mensajeError: LiveData<String?> = _mensajeError

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _datosCargados = MutableLiveData(false)
    val datosCargados: LiveData<Boolean> = _datosCargados



    fun actualizarBusqueda(query: String) {
        _searchQuery.value = query
    }

    fun selectScreen(pantalla: Int) {
        _selectedScreen.value = pantalla
    }

    fun cargarDatos(idUsuario: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            _datosCargados.value = false

            val tipos = homeUseCase.getTipoActividades()
            val actividades = homeUseCase.getActividades(idUsuario)

            if (tipos?.status == "success" && actividades?.status == "success") {
                _tipoActividades.value = tipos
                _actividades.value = actividades
                _datosCargados.value = true
            } else {
                _mensajeError.value =
                    tipos?.message ?: actividades?.message ?: "Error al cargar los datos"
            }

            _isLoading.value = false
        }
    }

}