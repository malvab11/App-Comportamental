package com.example.behaveapp.ui.data.init

import com.example.behaveapp.data.models.LoginResponse

data class LoginState (
    val usuario: String = "",
    val contrasena: String = "",
    val isShown: Boolean = false,
    val isEnabled: Boolean = false,
    val isLoading: Boolean = false,
    val loginResponse: LoginResponse? = null
)