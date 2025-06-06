package com.example.behaveapp.ui.screens.init

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.navigation.NavController
import com.example.behaveapp.data.screensNavigation.screensNavigation
import com.example.behaveapp.ui.screens.commons.CommonCircularProgress
import com.example.behaveapp.ui.theme.BlackEndBackground
import com.example.behaveapp.ui.theme.BlackStartBackground
import kotlinx.coroutines.delay

@Composable
fun LoadingScreen(modifier: Modifier = Modifier, navController: NavController) {

    LaunchedEffect(Unit) {
        delay(5000)
        navController.navigate(screensNavigation.HomeScreen.ruta){
            popUpTo(navController.graph.startDestinationId) { inclusive = true }
        }
    }

    Scaffold(
        topBar = {},
        bottomBar = {}
    ) { innerPadding ->

        Column(
            modifier = modifier
                .fillMaxSize()
                .background(
                    Brush.linearGradient(listOf(BlackStartBackground, BlackEndBackground))
                )
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CommonCircularProgress()
        }
    }
}