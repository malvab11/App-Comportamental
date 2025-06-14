package com.example.behaveapp.ui.data

import com.example.behaveapp.data.models.LoginResponse

data class LoginState (
    val usuario: String = "",
    val contrasena: String = "",
    val isShown: Boolean = false,
    val isEnabled: Boolean = false,
    val isLoading: Boolean = false,
    val mensaje: String? = null,
    val loginResponse: LoginResponse? = null
)