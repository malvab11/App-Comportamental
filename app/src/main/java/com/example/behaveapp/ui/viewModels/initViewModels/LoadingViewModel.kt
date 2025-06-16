package com.example.behaveapp.ui.viewModels.initViewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.behaveapp.data.models.Actividades
import com.example.behaveapp.data.models.TipoActividades
import com.example.behaveapp.domain.homeUseCase.ActividadesUseCase
import com.example.behaveapp.domain.homeUseCase.ReporteUseCase
import com.example.behaveapp.domain.loginUseCase.LoginUseCase
import com.example.behaveapp.ui.data.init.LoadingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDate
import javax.inject.Inject

@HiltViewModel
class LoadingViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val reporteUseCase: ReporteUseCase,
    private val actividadesUseCase: ActividadesUseCase
) : ViewModel() {
    private val _variables = MutableStateFlow(LoadingState())
    val variables: StateFlow<LoadingState> = _variables.asStateFlow()
    private var loadedCount = 0

    private fun checkAllLoaded() {
        if (loadedCount == 3) {
            _variables.update { it.copy(isDataCompleted = true) }
        }
    }

    fun clearUser() = viewModelScope.launch { loginUseCase.clearUser() }

    fun userValidate() {
        viewModelScope.launch {
            val usuario = loginUseCase.getUsuario()
            Log.i("usuario", usuario.toString())

            if (usuario != null) {
                _variables.update {
                    it.copy(
                        isLogged = true,
                        isLoading = false,
                        loadingData = usuario
                    )
                }
                loadedCount++
                checkAllLoaded()
            } else {
                _variables.update { it.copy(isLoading = false, isLogged = false) }
            }
        }
    }

    fun getInfo() {
        viewModelScope.launch {
            val idUsuario = _variables.value.loadingData?.idUsuario ?: 0
            val fechaHoy = LocalDate.now()
            val anio = fechaHoy.year.toString()
            val mes = "%02d".format(fechaHoy.monthValue)
            val response = reporteUseCase.getReporte(
                "obtenerInfoCompleta",
                idUsuario = idUsuario,
                anio = anio,
                mes = mes
            )
            _variables.update { it.copy(reportData = response) }
            if (response != null) {
                loadedCount++
                checkAllLoaded()
            }
        }
    }

    fun getTipoActividades() {
        viewModelScope.launch {
            val idUsuario = _variables.value.loadingData?.idUsuario ?: 0
            val response =
                actividadesUseCase.getTipoActividades(
                    accion = "getTipoTarea",
                    idUsuario = idUsuario
                )
            val tipoActividades = response?.data ?: emptyList()
            _variables.update {
                it.copy(tipoActividades = tipoActividades.map { tipoActividad ->
                    TipoActividades(
                        idTipoActividad = tipoActividad.idTipoActividad,
                        descripcion = tipoActividad.descripcion,
                        detalle = tipoActividad.detalle ?: null
                    )
                })
            }
            if (response != null) {
                loadedCount++
                checkAllLoaded()
            }
        }
    }

    fun getActividadesList() {
        viewModelScope.launch {
            val idUsuario = _variables.value.loadingData?.idUsuario ?: 0
            val response =
                actividadesUseCase.getActividadesList(
                    accion = "getActividades",
                    idUsuario = idUsuario
                )
            val actividades = response?.data ?: emptyList()
            _variables.update {
                it.copy(actividades = actividades.map { actividad ->
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
                })
            }
            if (response != null) {
                loadedCount++
                checkAllLoaded()
            }
        }
    }
}