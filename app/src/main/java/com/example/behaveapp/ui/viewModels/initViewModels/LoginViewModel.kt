package com.example.behaveapp.ui.viewModels.initViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.behaveapp.domain.loginUseCase.LoginUseCase
import com.example.behaveapp.ui.data.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {

    private val _variables = MutableStateFlow(LoginState())
    val variables: StateFlow<LoginState> = _variables.asStateFlow()

    fun resetValues(){
        _variables.value = LoginState()
    }

    fun onValueChange(usuario: String, contrasena: String) {
        _variables.update {
            it.copy(
                usuario = usuario,
                contrasena = contrasena,
                isEnabled = usuario.isNotBlank() && contrasena.isNotBlank()
            )
        }
    }

    fun showPassword() {
        _variables.update { it.copy(isShown = !it.isShown) }
    }

    fun clearMessage() {
        _variables.update { it.copy(mensaje = null) }
    }

    fun validateLogin() {
        viewModelScope.launch {
            _variables.update { it.copy(isLoading = true) }
            val response = loginUseCase.login(
                accion = "login",
                usuario = _variables.value.usuario.trim(),
                contrasena = _variables.value.contrasena.trim()
            )
            val mensajeFinal = response?.message ?: "Error en el login, vuelve a intentar"
            _variables.update {
                it.copy(
                    loginResponse = response,
                    mensaje = mensajeFinal,
                    isLoading = false
                )
            }
        }
    }
}
