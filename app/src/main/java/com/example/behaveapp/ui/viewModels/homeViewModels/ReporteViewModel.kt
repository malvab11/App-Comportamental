package com.example.behaveapp.ui.viewModels.homeViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.behaveapp.domain.homeUseCase.ReporteUseCase
import com.example.behaveapp.ui.data.home.ReporteState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReporteViewModel @Inject constructor(private val reporteUseCase: ReporteUseCase) :
    ViewModel() {
    private val _variables = MutableStateFlow(ReporteState())
    val variables: StateFlow<ReporteState> = _variables.asStateFlow()

    fun getUsuario(){
        if (_variables.value.usuario != null) return
        viewModelScope.launch {
            val response = reporteUseCase.getUsuarioDB()
            _variables.update { it.copy(usuario = response) }
        }
    }
    fun getAlumnos(){
        if (_variables.value.alumnos != null) return
        viewModelScope.launch {
            val response = reporteUseCase.getAlumnosDB()
            _variables.update { it.copy(alumnos = response) }
        }
    }
}