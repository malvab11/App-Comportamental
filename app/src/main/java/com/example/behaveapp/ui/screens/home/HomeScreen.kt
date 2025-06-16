package com.example.behaveapp.ui.screens.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.behaveapp.ui.screens.commons.CommonText
import com.example.behaveapp.ui.theme.DarkOrange
import com.example.behaveapp.ui.theme.DarkUnselectedItems
import com.example.behaveapp.ui.viewModels.homeViewModels.ActivityViewModel
import com.example.behaveapp.ui.viewModels.homeViewModels.ReporteViewModel
import com.example.behaveapp.ui.viewModels.initViewModels.LoadingViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    activityViewModel: ActivityViewModel,
    reporteViewModel: ReporteViewModel
) {

    var pantalla by rememberSaveable { mutableIntStateOf(0) }

    Scaffold(
        modifier = modifier,
        bottomBar = {
            MyBottomBar(selectedScreen = pantalla) {
                pantalla = it
            }
        }
    ) { padding ->

        when (pantalla) {
            0 -> ReportScreen(padding = padding, reporteViewModel = reporteViewModel)
            1 -> ActivitiesScreen(
                padding = padding,
                navController = navController,
                viewModel = activityViewModel
            )

            2 -> ProfileScreen(padding = padding)
        }
    }

}

@Composable
fun MyBottomBar(selectedScreen: Int, onClick: (Int) -> Unit) {

    //Declaracion de pantallas
    val screens = listOf(
        Icons.Default.Home to "Inicio",
        Icons.AutoMirrored.Outlined.List to "Actividades",
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