package com.example.behaveapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.behaveapp.data.screensNavigation.ScreenNavigation
import com.example.behaveapp.ui.screens.home.HomeScreen
import com.example.behaveapp.ui.screens.home.createActivity.CreateActivityScreen
import com.example.behaveapp.ui.screens.init.PresentationScreen
import com.example.behaveapp.ui.screens.init.login.LoginTutorScreen
import com.example.behaveapp.ui.screens.init.register.RegisterTutorScreen
import com.example.behaveapp.ui.screens.init.register.RegisterUserScreem
import com.example.behaveapp.ui.viewModels.homeViewModels.ActivityViewModel
import com.example.behaveapp.ui.viewModels.homeViewModels.CreateViewModel
import com.example.behaveapp.ui.viewModels.homeViewModels.HomeViewModel
import com.example.behaveapp.ui.viewModels.initViewModels.LoginViewModel
import com.example.behaveapp.ui.viewModels.initViewModels.PresentationViewModel
import com.example.behaveapp.ui.viewModels.initViewModels.RegisterViewModel

@Composable
fun ScreenController(
    presentationViewModel: PresentationViewModel,
    loginViewModel: LoginViewModel,
    registerViewModel: RegisterViewModel,
    homeViewModel: HomeViewModel,
    activityViewModel: ActivityViewModel,
    createViewModel: CreateViewModel
) {

    val navigationController = rememberNavController()
    NavHost(
        navController = navigationController,
        startDestination = ScreenNavigation.PresentationScreen.ruta
    ) {
        composable(ScreenNavigation.PresentationScreen.ruta) {
            PresentationScreen(navController = navigationController, presentationViewModel = presentationViewModel)
        }
        composable(ScreenNavigation.LoginTutorScreen.ruta) {
            LoginTutorScreen(navController = navigationController, loginViewModel = loginViewModel)
        }
        composable(ScreenNavigation.RegisterTutorScreen.ruta) {
            RegisterTutorScreen(
                navController = navigationController,
                registerViewModel = registerViewModel
            )
        }
        composable(ScreenNavigation.RegisterUserScreen.ruta) {
            RegisterUserScreem(navController = navigationController, registerViewModel = registerViewModel)
        }
        composable(ScreenNavigation.HomeScreen.ruta) { datos ->
            val idUsuario = datos.arguments?.getString("idUsuario")
            val tipoUsuario = datos.arguments?.getString("tipoUsuario")
            HomeScreen(navController = navigationController, homeViewModel = homeViewModel,activityViewModel = activityViewModel, idUsuario = idUsuario?.toInt() ?: 1, tipoUsuario = tipoUsuario?.toInt() ?: 1)
        }
        composable(ScreenNavigation.CreateActivityScreen.ruta) {
            CreateActivityScreen(
                navController = navigationController,
                createViewModel = createViewModel
            )
        }
    }
}