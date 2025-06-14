package com.example.behaveapp.ui.data.init

import com.example.behaveapp.data.models.RegisterResponse

data class RegisterState(
    val correo: String = "",
    val celular: String = "",
    val nombres: String = "",
    val contrasena: String = "",
    val contrasenaRepetida: String = "",
    val codigoFamilia: String = "",
    val isEnabled: Boolean = false,
    val isShown: Boolean = false,
    val isLoading: Boolean = false,
    val errorContrasena: String? = null,
    val registerResponse: RegisterResponse? = null
)
