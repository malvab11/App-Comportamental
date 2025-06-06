package com.example.behaveapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.behaveapp.data.screensNavigation.screensNavigation
import com.example.behaveapp.ui.screens.home.ActivitiesScreen
import com.example.behaveapp.ui.screens.home.HomeScreen
import com.example.behaveapp.ui.screens.home.ProfileScreen
import com.example.behaveapp.ui.screens.home.ReportScreen
import com.example.behaveapp.ui.screens.init.LoadingScreen
import com.example.behaveapp.ui.screens.init.login.LoginTutorScreen
import com.example.behaveapp.ui.screens.init.login.LoginUserScreen
import com.example.behaveapp.ui.screens.init.PresentationScreen
import com.example.behaveapp.ui.screens.init.register.RegisterTutorScreen

@Composable
fun ScreenController(modifier: Modifier = Modifier) {
    val navigationController = rememberNavController()
    NavHost(
        navController = navigationController,
        startDestination = screensNavigation.PresentationScreen.ruta
    ) {
        composable(screensNavigation.PresentationScreen.ruta) {
            PresentationScreen(navController = navigationController)
        }
        composable(screensNavigation.LoginTutorScreen.ruta) {
            LoginTutorScreen(navController = navigationController)
        }
        composable(screensNavigation.RegisterTutorScreen.ruta) {
            RegisterTutorScreen(navController = navigationController)
        }
        composable(screensNavigation.LoginUserScreen.ruta) {
            LoginUserScreen(navController = navigationController)
        }
        composable(screensNavigation.LoadingScreen.ruta) {
            LoadingScreen(navController = navigationController)
        }
        composable(screensNavigation.HomeScreen.ruta) {
            HomeScreen(navController = navigationController)
        }
    }
}