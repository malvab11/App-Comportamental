package com.example.behaveapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.behaveapp.data.screensNavigation.screensNavigation

@Composable
fun ScreenController(modifier: Modifier = Modifier) {
    val navigationController = rememberNavController()
    NavHost(
        navController = navigationController,
        startDestination = screensNavigation.RegisterTutorScreen.ruta
    ) {
        composable(screensNavigation.PresentationScreen.ruta) {
            PresentationScreen()
        }
        composable(screensNavigation.LoginTutorScreen.ruta) {
            LoginTutorScreen()
        }
        composable(screensNavigation.RegisterTutorScreen.ruta) {
            RegisterTutorScreen()
        }
    }
}