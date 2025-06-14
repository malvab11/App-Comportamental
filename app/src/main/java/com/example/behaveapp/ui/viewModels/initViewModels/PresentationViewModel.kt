package com.example.behaveapp.ui.viewModels.initViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.behaveapp.data.room.entity.UsuarioEntity
import com.example.behaveapp.domain.loginUseCase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PresentationViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {

    private val _isLoggedIn = MutableStateFlow<Boolean?>(null)
    val isLoggedIn: StateFlow<Boolean?> = _isLoggedIn

    private val _valor = MutableStateFlow<UsuarioEntity?>(null)
    val valor: StateFlow<UsuarioEntity?> = _valor

    fun validarUsuarioGuardado() {
        viewModelScope.launch {
            val usuario = loginUseCase.getUsuario()
            _valor.value = usuario
            _isLoggedIn.value = usuario != null
        }
    }
}