package com.example.behaveapp.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.behaveapp.R
import com.example.behaveapp.ui.screens.commons.CommonCard
import com.example.behaveapp.ui.screens.commons.CommonCircularProgress
import com.example.behaveapp.ui.screens.commons.CommonSpacer
import com.example.behaveapp.ui.screens.commons.CommonText
import com.example.behaveapp.ui.screens.commons.CommonUserCard
import com.example.behaveapp.ui.theme.BlackEndBackground
import com.example.behaveapp.ui.theme.BlackStartBackground

@Composable
fun ReportScreen(modifier: Modifier = Modifier, padding: PaddingValues, isLoadingData: Boolean) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(listOf(BlackStartBackground, BlackEndBackground))
            )
            .padding(
                top = padding.calculateTopPadding(),
                bottom = padding.calculateBottomPadding(),
                start = 10.dp,
                end = 10.dp
            )
    ) {
        if(isLoadingData){
            CommonCircularProgress()
        }else{
            ReportContent()
        }
    }
}

@Composable
private fun ReportContent() {
    Header()
    Users()
    Details()
}

@Composable
private fun Header() {
    CommonText(text = "Bienvenido Marlon", fontSize = 24, fontWeight = FontWeight.Bold)
    CommonSpacer(size = 10)
    CommonCard(
        texto = "Codigo de Familia : 021932",
        icono = R.drawable.ic_copy,
        contentDescription = "Copiar Codigo de Familia"
    )
    CommonSpacer(size = 10)

    CommonCard(
        texto = "Mes : Junio",
        icono = R.drawable.ic_arrow_down,
        contentDescription = "Menu Desplegable de Mes"
    )
    CommonSpacer(size = 10)
}

@Composable
private fun Users() {

    CommonText(text = "Solicitudes", fontSize = 20, fontWeight = FontWeight.Bold)
    CommonSpacer(size = 10)

    val requests = listOf(
        Triple("Saul", false, 1)
    )

    LazyColumn(
        modifier = Modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(requests) { (tarea, selected, puntaje) ->
            CommonUserCard(
                tareas = tarea,
                selected = selected,
                puntaje = puntaje,
                tipo = 2,
                descripcion2 = "Solicitud de Union"
            )
        }
    }
    CommonSpacer(size = 20)

    CommonText(text = "Usuarios", fontSize = 20, fontWeight = FontWeight.Bold)
    CommonSpacer(size = 10)

    val users = listOf(
        Triple("Marlon", true, 2),
        Triple("Paolo", false, 3),
    )

    LazyColumn(
        modifier = Modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(users) { (tarea, selected, puntaje) ->
            CommonUserCard(
                tareas = tarea,
                selected = selected,
                puntaje = puntaje
            )
        }
    }

    CommonSpacer(size = 20)

}

@Composable
private fun Details() {
    CommonText(text = "Total de Puntos : 120", fontSize = 20, fontWeight = FontWeight.Bold)
    CommonSpacer(size = 10)
}
