package com.example.behaveapp.ui.screens.init.loading

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.behaveapp.data.screensNavigation.ScreenNavigation
import com.example.behaveapp.ui.screens.commons.CommonCircularProgress
import com.example.behaveapp.ui.screens.commons.CommonOutlinedButtons
import com.example.behaveapp.ui.theme.BlackEndBackground
import com.example.behaveapp.ui.theme.BlackStartBackground
import com.example.behaveapp.ui.viewModels.initViewModels.LoadingViewModel

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    loadingViewModel: LoadingViewModel
) {
    val variables by loadingViewModel.variables.collectAsState()

    LaunchedEffect(Unit) {
        loadingViewModel.userValidate()
    }

    LaunchedEffect(variables.isLoading) {
        if (!variables.isLoading) {
            if (variables.isLogged == true) {
                loadingViewModel.getInfo()
                loadingViewModel.getTipoActividades()
                loadingViewModel.getActividadesList()
            } else {
                navController.navigate(ScreenNavigation.PresentationScreen.ruta)
            }
        }
    }

    LaunchedEffect(variables.isDataCompleted ) {
        if (variables.isDataCompleted) {
            navController.navigate(ScreenNavigation.HomeScreen.ruta)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    listOf(BlackStartBackground, BlackEndBackground),
                )
            ), contentAlignment = Alignment.Center
    ) {
        CommonCircularProgress()
        CommonOutlinedButtons(modifier = Modifier, texto = "Eliminar Data", containterColor = Color.Red, tamanoTexto = 12) {
            loadingViewModel.clearUser()
            navController.navigate(ScreenNavigation.PresentationScreen.ruta)
        }
    }
}

