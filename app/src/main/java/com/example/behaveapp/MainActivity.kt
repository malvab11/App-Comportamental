package com.example.behaveapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import com.example.behaveapp.ui.screens.ScreenController
import com.example.behaveapp.ui.theme.BehaveAppTheme
import com.example.behaveapp.ui.viewModels.homeViewModels.ActivityViewModel
import com.example.behaveapp.ui.viewModels.homeViewModels.CreateViewModel
import com.example.behaveapp.ui.viewModels.homeViewModels.ReporteViewModel
import com.example.behaveapp.ui.viewModels.initViewModels.LoadingViewModel
import com.example.behaveapp.ui.viewModels.initViewModels.LoginViewModel
import com.example.behaveapp.ui.viewModels.initViewModels.RegisterViewModel
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val loadingViewModel: LoadingViewModel by viewModels()
    private val loginViewModel: LoginViewModel by viewModels()
    private val registerViewModel: RegisterViewModel by viewModels()
    private val reporteViewModel: ReporteViewModel by viewModels()
    private val activityViewModel: ActivityViewModel by viewModels()
    private val createViewModel: CreateViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidThreeTen.init(this)
        enableEdgeToEdge()
        setContent {
            BehaveAppTheme(darkTheme = true) {
                ScreenController(
                    loadingViewModel = loadingViewModel,
                    loginViewModel = loginViewModel,
                    registerViewModel = registerViewModel,
                    reporteViewModel = reporteViewModel,
                    activityViewModel = activityViewModel,
                    createViewModel = createViewModel
                )
            }
        }
    }
}