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
import com.example.behaveapp.data.models.Actividades
import com.example.behaveapp.data.models.TipoActividades
import com.example.behaveapp.ui.screens.commons.CommonCard
import com.example.behaveapp.ui.screens.commons.CommonIcon
import com.example.behaveapp.ui.screens.commons.CommonSpacer
import com.example.behaveapp.ui.screens.commons.CommonTaskCard
import com.example.behaveapp.ui.screens.commons.CommonText
import com.example.behaveapp.ui.theme.BlackEndBackground
import com.example.behaveapp.ui.theme.BlackStartBackground
import com.example.behaveapp.ui.theme.DarkButtons

@Composable
fun ActivitiesScreen(
    modifier: Modifier = Modifier,
    padding: PaddingValues,
    navController: NavHostController,
    actividades: List<Actividades>?,
    tipoActividades: List<TipoActividades>?,
    searchQuery: String,
    onBuscar: (String) -> Unit
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
            ActivitiesContent(actividades = actividades, tipoActividades = tipoActividades,searchQuery = searchQuery,onBuscar = onBuscar)
        }

        FAB(
            modifier = Modifier
                .align(androidx.compose.ui.Alignment.BottomEnd)
                .padding(16.dp)
        )
    }
}


@Composable
private fun ActivitiesContent(
    actividades: List<Actividades>?,
    tipoActividades: List<TipoActividades>?,
    searchQuery: String,
    onBuscar: (String) -> Unit
) {
    Header(searchQuery = searchQuery, onBuscar = onBuscar)
    CommonSpacer(size = 20)
    ActivitiesGrouped(actividades, tipoActividades)
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
private fun ActivitiesGrouped(
    actividades: List<Actividades>?,
    tipoActividades: List<TipoActividades>?
) {
    val agrupado = actividades?.groupBy { it.tipoActividad }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        if (actividades.isNullOrEmpty() || tipoActividades.isNullOrEmpty()) {
            item {
                CommonText(
                    text = "No hay actividades disponibles",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16
                )
            }
            return@LazyColumn
        }
        agrupado?.forEach { (tipoId, actividadesLista) ->
            val titulo = tipoActividades.firstOrNull { it.id == tipoId }?.descripcion ?: "Otros"

            item {
                CommonText(text = titulo, fontSize = 20, fontWeight = FontWeight.Bold)
                CommonSpacer(size = 10)
            }

            items(actividadesLista) { actividad ->
                CommonTaskCard(
                    tareas = actividad.titulo,
                    done = false,
                    puntaje = actividad.puntaje
                )
            }

            item { CommonSpacer(size = 20) }
        }

        item { CommonSpacer(size = 40) }
    }
}



@Composable
private fun Header(modifier: Modifier = Modifier,searchQuery: String, onBuscar: (String) -> Unit) {
    CommonText(text = "Actividades", fontSize = 24, fontWeight = FontWeight.Bold)
    CommonSpacer(size = 10)
    CommonCard(
        texto = "Buscar",
        icono = R.drawable.ic_search,
        contentDescription = "Buscar Actividad"
    )
}
