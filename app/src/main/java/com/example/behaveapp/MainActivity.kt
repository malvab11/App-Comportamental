package com.example.behaveapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.behaveapp.ui.screens.ScreenController
import com.example.behaveapp.ui.viewModels.HomeViewModel
import com.example.behaveapp.ui.viewModels.initViewModels.LoginViewModel
import com.example.behaveapp.ui.theme.BehaveAppTheme
import com.example.behaveapp.ui.viewModels.initViewModels.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val loginViewModel: LoginViewModel by viewModels()
    private val registerViewModel: RegisterViewModel by viewModels()
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BehaveAppTheme {
                ScreenController(loginViewModel = loginViewModel, registerViewModel = registerViewModel, homeViewModel = homeViewModel)
            }
        }
    }
}