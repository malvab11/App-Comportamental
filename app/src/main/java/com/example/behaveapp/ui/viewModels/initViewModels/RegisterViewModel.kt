package com.example.behaveapp.ui.viewModels.initViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.behaveapp.data.models.RegisterTutorRequest
import com.example.behaveapp.data.models.RegisterUserRequest
import com.example.behaveapp.domain.loginUseCase.LoginUseCase
import com.example.behaveapp.ui.data.init.RegisterState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val registerUseCase: LoginUseCase) :
    ViewModel() {

    private val _variables = MutableStateFlow(RegisterState())
    val variables: StateFlow<RegisterState> = _variables.asStateFlow()

    fun showPassword() {
        _variables.update { it.copy(isShown = !it.isShown) }
    }

    fun resetValues(){
        _variables.value = RegisterState()
    }

    fun onValueChangeRegister(
        correo: String,
        celular: String,
        nombre: String,
        contrasena: String,
        contrasenaRepetida: String
    ) {
        val camposLlenos = listOf(correo,celular,nombre,contrasena,contrasenaRepetida).all { it.isNotBlank() }
        _variables.update { valores ->
            valores.copy(
                correo = correo,
                celular = celular,
                nombres = nombre,
                contrasena = contrasena,
                contrasenaRepetida = contrasenaRepetida,
                isEnabled = camposLlenos
            )
        }
    }

    fun onValueChangeRegisterUser(nombre: String, codigoFamilia: String) {
        val coincidencia = nombre.isNotBlank() && codigoFamilia.isNotBlank()
        _variables.update {
            it.copy(
                nombres = nombre,
                codigoFamilia = codigoFamilia,
                isEnabled = coincidencia
            )
        }
    }


    fun validateRegisterTutor() {
        _variables.update { it.copy(errorContrasena = null) }
        if(!checkPasswordsMatch())
            return
        viewModelScope.launch {
            _variables.update { it.copy(isLoading = true) }
            val data = registerUseCase.register(
                accion = "registroTutor",
                datos = RegisterTutorRequest(
                    accion = "registroTutor",
                    correo = _variables.value.correo.trim(),
                    celular = _variables.value.celular.trim(),
                    nombre = _variables.value.nombres.trim(),
                    contrasena = _variables.value.contrasena.trim()
                )
            )

            _variables.update {
                it.copy(
                    registerResponse = data,
                    isLoading = false
                )
            }
        }
    }

    fun validateRegisterUsuario() {
        viewModelScope.launch {
            _variables.update { it.copy(isLoading = true) }
            val data = registerUseCase.register(
                accion = "registroAlumno",
                datos = RegisterUserRequest(
                    accion = "registroTutor",
                    nombre = _variables.value.nombres.trim(),
                    codigoFamilia = _variables.value.codigoFamilia.trim()
                )
            )

            _variables.update {
                it.copy(
                    registerResponse = data,
                    isLoading = false
                )
            }
        }
    }

    private fun checkPasswordsMatch(): Boolean {
        val coincidencia = _variables.value.contrasena == _variables.value.contrasenaRepetida
        if (!coincidencia) {
            _variables.update { it.copy(errorContrasena = "Las contraseñas no coinciden") }
        }
        return coincidencia
    }

}