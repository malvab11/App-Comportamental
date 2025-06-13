package com.example.behaveapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.behaveapp.data.screensNavigation.screensNavigation
import com.example.behaveapp.ui.screens.home.HomeScreen
import com.example.behaveapp.ui.screens.init.LoadingScreen
import com.example.behaveapp.ui.screens.init.PresentationScreen
import com.example.behaveapp.ui.screens.init.login.LoginTutorScreen
import com.example.behaveapp.ui.screens.init.login.LoginUserScreen
import com.example.behaveapp.ui.screens.init.register.RegisterTutorScreen
import com.example.behaveapp.ui.screens.viewModels.HomeViewModel
import com.example.behaveapp.ui.screens.viewModels.initViewModels.LoginViewModel

@Composable
fun ScreenController(
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel,
    homeViewModel: HomeViewModel
) {

    val navigationController = rememberNavController()
    NavHost(
        navController = navigationController,
        startDestination = screensNavigation.HomeScreen.ruta
    ) {
        composable(screensNavigation.PresentationScreen.ruta) {
            PresentationScreen(navController = navigationController)
        }
        composable(screensNavigation.LoginTutorScreen.ruta) {
            LoginTutorScreen(navController = navigationController, loginViewModel = loginViewModel)
        }
        composable(screensNavigation.RegisterTutorScreen.ruta) {
            RegisterTutorScreen(
                navController = navigationController,
                loginViewModel = loginViewModel
            )
        }
        composable(screensNavigation.LoginUserScreen.ruta) {
            LoginUserScreen(navController = navigationController, loginViewModel = loginViewModel)
        }
        composable(screensNavigation.LoadingScreen.ruta) {
            LoadingScreen(navController = navigationController)
        }
        composable(screensNavigation.HomeScreen.ruta) {
            HomeScreen(navController = navigationController, homeViewModel = homeViewModel)
        }
    }
}