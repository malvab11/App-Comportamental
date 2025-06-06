package com.example.behaveapp.ui.screens.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.behaveapp.ui.screens.commons.CommonText
import com.example.behaveapp.ui.theme.DarkOrange
import com.example.behaveapp.ui.theme.DarkUnselectedItems

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {

    var pantalla by rememberSaveable { mutableStateOf(0) }

    Scaffold(
        modifier = modifier,
        topBar = { },
        bottomBar = { MyBottomBar(selectedScreen = pantalla) { pantalla = it } }
    ) { padding ->

        when (pantalla) {
            0 -> ReportScreen(padding = padding)
            1 -> ActivitiesScreen(padding = padding, navController = navController)
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