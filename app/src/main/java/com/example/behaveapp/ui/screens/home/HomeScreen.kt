package com.example.behaveapp.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.behaveapp.ui.screens.commons.CommonCircularProgress
import com.example.behaveapp.ui.screens.commons.CommonText
import com.example.behaveapp.ui.screens.viewModels.HomeViewModel
import com.example.behaveapp.ui.theme.BlackEndBackground
import com.example.behaveapp.ui.theme.BlackStartBackground
import com.example.behaveapp.ui.theme.DarkOrange
import com.example.behaveapp.ui.theme.DarkUnselectedItems

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    homeViewModel: HomeViewModel
) {

    val pantallas by homeViewModel.selectedScreen.observeAsState(0)
    val tipoActividades by homeViewModel.tipoActividades.observeAsState()
    val actividades by homeViewModel.actividades.observeAsState()
    val datosCargados by homeViewModel.datosCargados.observeAsState(false)

    LaunchedEffect(pantallas) {
        if (pantallas == 1) {
            homeViewModel.cargarDatos(idUsuario = 1)
        }
    }

    Scaffold(
        modifier = modifier,
        bottomBar = { MyBottomBar(selectedScreen = pantallas) { homeViewModel.selectScreen(it) } }
    ) { padding ->

        when (pantallas) {
            0 -> ReportScreen(padding = padding)
            1 -> if (!datosCargados) {
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .background(
                            Brush.linearGradient(listOf(BlackStartBackground, BlackEndBackground))
                        )
                        .padding(padding),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CommonCircularProgress()
                }
            } else {
                ActivitiesScreen(
                    padding = padding,
                    navController = navController,
                    tipoActividades = tipoActividades?.tipoActividades,
                    actividades = actividades?.actividades
                )
            }
            2 -> ProfileScreen(padding = padding)
        }
    }

}

@Composable
fun MyBottomBar(selectedScreen: Int, onClick: (Int) -> Unit) {

    //Declaracion de pantallas
    val screens = listOf(
        Icons.Default.Home to "Inicio",
        Icons.Default.List to "Actividades",
        Icons.Default.Person to "Perfil"
    )

    NavigationBar(
        containerColor = DarkUnselectedItems,
        contentColor = Color.White
    ) {
        screens.forEachIndexed { intPantalla, (icono, titulo) ->
            NavigationBarItem(
                selected = selectedScreen == intPantalla,
                onClick = { onClick(intPantalla) },
                icon = {
                    Icon(
                        imageVector = icono,
                        contentDescription = titulo,
                        tint = DarkOrange.takeIf { selectedScreen == intPantalla } ?: Color.White
                    )
                },
                label = {
                    CommonText(
                        modifier = Modifier,
                        text = titulo,
                        fontSize = 12,
                        color = DarkOrange.takeIf { selectedScreen == intPantalla } ?: Color.White
                    )
                }
            )
        }
    }
}