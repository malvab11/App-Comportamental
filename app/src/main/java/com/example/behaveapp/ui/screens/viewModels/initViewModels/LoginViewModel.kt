package com.example.behaveapp.ui.screens.viewModels.initViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.behaveapp.data.models.LoginResponse
import com.example.behaveapp.data.models.RegisterResponse
import com.example.behaveapp.domain.loginUseCase.LoginUseCase
import com.example.behaveapp.domain.registerUseCases.RegisterUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val registerUseCases: RegisterUseCases
) : ViewModel() {

    // Inputs
    private val _usuario = MutableLiveData("")
    val usuario: LiveData<String> = _usuario

    private val _nombres = MutableLiveData("")
    val nombres: LiveData<String> = _nombres

    private val _codigoFamilia = MutableLiveData("")
    val codigoFamilia: LiveData<String> = _codigoFamilia

    private val _contrasena = MutableLiveData("")
    val contrasena: LiveData<String> = _contrasena

    private val _repeatContrasena = MutableLiveData("")
    val repeatContrasena: LiveData<String> = _repeatContrasena

    private val _isShown = MutableLiveData(false)
    val isShown: LiveData<Boolean> = _isShown

    private val _isEnabled = MutableLiveData(false)
    val isEnabled: LiveData<Boolean> = _isEnabled

    // Estados de respuesta
    private val _loginResponse = MutableLiveData<LoginResponse?>()
    val loginResponse: LiveData<LoginResponse?> = _loginResponse

    private val _registerResponse = MutableLiveData<RegisterResponse?>()
    val registerResponse: LiveData<RegisterResponse?> = _registerResponse

    private val _errorContrasena = MutableLiveData<String?>()
    val errorContrasena: LiveData<String?> = _errorContrasena

    private val _mensajeError = MutableLiveData<String?>()
    val mensajeError: LiveData<String?> = _mensajeError

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    // ──────────────────────────────────────
    // Funciones públicas
    // ──────────────────────────────────────

    fun onValueChange(usuario: String, contrasena: String) {
        _usuario.value = usuario
        _contrasena.value = contrasena
        _isEnabled.value = usuario.isNotBlank() && contrasena.isNotBlank()
    }

    fun onValueChangeRegister(usuario: String, nombre: String, pass: String, repeatPass: String) {
        _usuario.value = usuario
        _nombres.value = nombre
        _contrasena.value = pass
        _repeatContrasena.value = repeatPass
        _isEnabled.value = listOf(usuario, nombre, pass, repeatPass).all { it.isNotBlank() }
    }

    fun onValueChangeRegisterUser(nombre: String, codigoFamilia: String){
        _nombres.value = nombre
        _codigoFamilia.value = codigoFamilia
        _isEnabled.value = nombre.isNotBlank() && codigoFamilia.isNotBlank()
    }

    fun showPassword() {
        _isShown.value = !(_isShown.value ?: false)
    }

    fun clearMensajeError() {
        _mensajeError.value = null
        _errorContrasena.value = null
    }

    fun validateLogin() {
        val user = _usuario.value.orEmpty()
        val pass = _contrasena.value.orEmpty()

        if (user.isBlank() || pass.isBlank()) {
            _mensajeError.value = "Debe completar los campos."
            return
        }

        viewModelScope.launch {
            _isLoading.value = true
            val data = loginUseCase.login(user.trim(), pass.trim())
            _isLoading.value = false

            if (data?.status == "success") {
                _loginResponse.value = data
            } else {
                _mensajeError.value = data?.message ?: "Error en el Login"
            }
        }
    }

    fun validateRegister() {
        val user = _usuario.value.orEmpty()
        val name = _nombres.value.orEmpty()
        val pass = _contrasena.value.orEmpty()
        val repeatPass = _repeatContrasena.value.orEmpty()

        if (!checkPasswordsMatch(pass, repeatPass)) return

        viewModelScope.launch {
            _isLoading.value = true
            val data = registerUseCases.registerTutor(nombre = name.trim(), correo = if(user.contains(".com")) user.trim() else "", celular = if(!user.contains(".com")) user.trim() else "", contrasena = pass.trim())
            _isLoading.value = false

            if (data?.status == "success") {
                _registerResponse.value = data
            } else {
                _mensajeError.value = data?.message ?: "Error al registrar usuario"
            }
        }
    }

    fun validateRegisterUser() {
        val name = _nombres.value.orEmpty()
        val codFamilia = _codigoFamilia.value.orEmpty()

        viewModelScope.launch {
            _isLoading.value = true
            val data = registerUseCases.registerUser(nombre = name.trim(), codigoFamilia = codFamilia.trim())
            _isLoading.value = false

            if (data?.status == "success") {
                _registerResponse.value = data
            } else {
                _mensajeError.value = data?.message ?: "Error al ingresar a la familia"
            }
        }
    }

    // ──────────────────────────────────────
    // Funciones privadas
    // ──────────────────────────────────────

    private fun checkPasswordsMatch(pass: String, repeatPass: String): Boolean {
        return if (pass == repeatPass) {
            _errorContrasena.value = null
            true
        } else {
            _errorContrasena.value = "Las contraseñas no coinciden"
            false
        }
    }
}
