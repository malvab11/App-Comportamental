package com.example.behaveapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.behaveapp.data.screensNavigation.ScreenNavigation
import com.example.behaveapp.ui.screens.home.HomeScreen
import com.example.behaveapp.ui.screens.home.createActivity.CreateActivityScreen
import com.example.behaveapp.ui.screens.init.PresentationScreen
import com.example.behaveapp.ui.screens.init.loading.LoadingScreen
import com.example.behaveapp.ui.screens.init.login.LoginTutorScreen
import com.example.behaveapp.ui.screens.init.register.RegisterTutorScreen
import com.example.behaveapp.ui.screens.init.register.RegisterUserScreem
import com.example.behaveapp.ui.viewModels.homeViewModels.ActivityViewModel
import com.example.behaveapp.ui.viewModels.homeViewModels.CreateViewModel
import com.example.behaveapp.ui.viewModels.homeViewModels.ReporteViewModel
import com.example.behaveapp.ui.viewModels.initViewModels.LoadingViewModel
import com.example.behaveapp.ui.viewModels.initViewModels.LoginViewModel
import com.example.behaveapp.ui.viewModels.initViewModels.RegisterViewModel

@Composable
fun ScreenController(
    loadingViewModel: LoadingViewModel,
    loginViewModel: LoginViewModel,
    registerViewModel: RegisterViewModel,
    reporteViewModel: ReporteViewModel,
    activityViewModel: ActivityViewModel,
    createViewModel: CreateViewModel
) {

    val navigationController = rememberNavController()
    NavHost(
        navController = navigationController,
        startDestination = ScreenNavigation.LoadingScreen.ruta
    ) {
        composable(ScreenNavigation.LoadingScreen.ruta) {
            LoadingScreen(navController = navigationController, loadingViewModel = loadingViewModel)
        }
        composable(ScreenNavigation.PresentationScreen.ruta) {
            PresentationScreen(navController = navigationController)
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
            RegisterUserScreem(
                navController = navigationController,
                registerViewModel = registerViewModel
            )
        }
        composable(ScreenNavigation.HomeScreen.ruta) {
            HomeScreen(
                navController = navigationController,
                activityViewModel = activityViewModel,
                reporteViewModel = reporteViewModel
            )
        }
        composable(
            route = ScreenNavigation.CreateActivityScreen.ruta,
            arguments = listOf(navArgument("idActividad") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val idActividad = backStackEntry.arguments?.getString("idActividad") ?: "0"

            CreateActivityScreen(
                navController = navigationController,
                createViewModel = createViewModel,
                idActividad = idActividad
            )
        }
    }
}