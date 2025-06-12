package com.example.behaveapp.ui.screens.viewModels.initViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.behaveapp.data.models.LoginResponse
import com.example.behaveapp.domain.loginUseCase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {

    //Inputs
    private val _usuario = MutableLiveData<String>()
    val usuario: LiveData<String> = _usuario
    private val _contrasena = MutableLiveData<String>()
    val contrasena: LiveData<String> = _contrasena
    private val _isEnabled = MutableLiveData<Boolean>()
    val isEnabled: LiveData<Boolean> = _isEnabled
    //Respuestas
    private val _loginResponse = MutableLiveData<LoginResponse?>()
    val loginResponse: LiveData<LoginResponse?> = _loginResponse
    private val _mensajeError = MutableLiveData<String?>()
    val mensajeError: LiveData<String?> = _mensajeError
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun onValueChange(usuario: String, contrasena: String){
        _usuario.value = usuario
        _contrasena.value = contrasena
        _isEnabled.value = enableLogin(usuario, contrasena)
    }

    fun enableLogin(usuario: String, contrasena: String) = usuario.isNotEmpty() && contrasena.isNotEmpty()

    fun clearMensajeError() {
        _mensajeError.value = null
    }

    fun validateLogin(usuario: String, contrasena: String) {
        viewModelScope.launch {
            _isLoading.value = true

            val data = loginUseCase.login(usuario, contrasena)

            _isLoading.value = false

            if (data != null && data.status == "success") {
                _loginResponse.value = data
            } else {
                _mensajeError.value = data?.message ?: "Error en el Login"
            }
        }
    }

}