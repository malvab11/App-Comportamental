package com.example.behaveapp.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.behaveapp.R
import com.example.behaveapp.ui.screens.commons.CommonCard
import com.example.behaveapp.ui.screens.commons.CommonIcon
import com.example.behaveapp.ui.screens.commons.CommonSpacer
import com.example.behaveapp.ui.screens.commons.CommonTaskCard
import com.example.behaveapp.ui.screens.commons.CommonText
import com.example.behaveapp.ui.theme.BlackEndBackground
import com.example.behaveapp.ui.theme.BlackStartBackground
import com.example.behaveapp.ui.theme.DarkButtons
import com.example.behaveapp.ui.theme.DarkUnselectedItems

@Composable
fun ActivitiesScreen(
    modifier: Modifier = Modifier,
    padding: PaddingValues,
    navController: NavHostController
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Brush.linearGradient(listOf(BlackStartBackground, BlackEndBackground)))
            .padding(
                top = padding.calculateTopPadding(),
                bottom = padding.calculateBottomPadding(),
                start = 10.dp,
                end = 10.dp
            )
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            ActivitiesContent()
        }

        FAB(modifier = Modifier
            .align(androidx.compose.ui.Alignment.BottomEnd)
            .padding(16.dp)
        )
    }
}


@Composable
private fun ActivitiesContent() {
    Header()
    CommonSpacer(size = 20)
    Activities()

}

@Composable
private fun FAB(modifier: Modifier = Modifier) {
    FloatingActionButton(
        onClick = { /* Acción */ },
        modifier = modifier,
        containerColor = DarkButtons
    ) {
        CommonIcon(
            size = 14,
            icon = R.drawable.ic_plus,
            contentDescription = "Añadir actividad",
            tint = Color.White
        )
    }
}


@Composable
private fun Activities() {
    val tasks = listOf(
        Triple("Hacer la cama", false, 2),
        Triple("Participar en clase", false, 3),
        Triple("Limpiar el cuarto", false, 1)
    )

    val premios = listOf(
        Triple("Salida al cine", false, 2),
        Triple("Compra de Lego", false, 3),
        Triple("Noche de Pizza", false, 1)
    )

    val penalidades = listOf(
        Triple("Mala conducta", false, 10)
    )

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            CommonText(text = "Solicitudes", fontSize = 20, fontWeight = FontWeight.Bold)
            CommonSpacer(size = 10)
        }

        items(tasks) { (tarea, done, puntaje) ->
            CommonTaskCard(
                tareas = tarea,
                done = done,
                puntaje = puntaje
            )
        }

        item {
            CommonSpacer(size = 20)
            CommonText(text = "Premios", fontSize = 20, fontWeight = FontWeight.Bold)
            CommonSpacer(size = 10)
        }

        items(premios) { (tarea, done, puntaje) ->
            CommonTaskCard(
                tareas = tarea,
                done = done,
                puntaje = puntaje
            )
        }

        item {
            CommonSpacer(size = 20)
            CommonText(text = "Penalidades", fontSize = 20, fontWeight = FontWeight.Bold)
            CommonSpacer(size = 10)
        }

        items(penalidades) { (tarea, done, puntaje) ->
            CommonTaskCard(
                tareas = tarea,
                done = done,
                puntaje = puntaje
            )
        }

        item { CommonSpacer(size = 40) }
    }
}


@Composable
private fun Header(modifier: Modifier = Modifier) {
    CommonText(text = "Actividades", fontSize = 24, fontWeight = FontWeight.Bold)
    CommonSpacer(size = 10)
    CommonCard(
        texto = "Buscar",
        icono = R.drawable.ic_search,
        contentDescription = "Buscar Actividad"
    )
}
