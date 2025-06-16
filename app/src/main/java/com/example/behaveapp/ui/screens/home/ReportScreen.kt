package com.example.behaveapp.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.behaveapp.R
import com.example.behaveapp.data.room.entity.AlumnoEntity
import com.example.behaveapp.ui.data.home.ReporteState
import com.example.behaveapp.ui.data.init.LoadingState
import com.example.behaveapp.ui.screens.commons.CommonCard
import com.example.behaveapp.ui.screens.commons.CommonCircularProgress
import com.example.behaveapp.ui.screens.commons.CommonIcon
import com.example.behaveapp.ui.screens.commons.CommonSpacer
import com.example.behaveapp.ui.screens.commons.CommonTaskCard
import com.example.behaveapp.ui.screens.commons.CommonText
import com.example.behaveapp.ui.screens.commons.CommonUserCard
import com.example.behaveapp.ui.theme.BlackEndBackground
import com.example.behaveapp.ui.theme.BlackStartBackground
import com.example.behaveapp.ui.theme.DarkOrange
import com.example.behaveapp.ui.theme.DarkSelectedItems
import com.example.behaveapp.ui.theme.DarkUnselectedItems
import com.example.behaveapp.ui.viewModels.homeViewModels.ReporteViewModel
import com.example.behaveapp.ui.viewModels.initViewModels.LoadingViewModel

@Composable
fun ReportScreen(modifier: Modifier = Modifier, padding: PaddingValues, reporteViewModel: ReporteViewModel) {

    val variables by reporteViewModel.variables.collectAsState()

    LaunchedEffect(Unit) {
        reporteViewModel.getUsuario()
        reporteViewModel.getAlumnos()
    }

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
//        if (1==1) {
//            CommonCircularProgress()
//        } else {
//            if (variables.isShownAlertDialog) {
//                AlertDialog(
//                    onDismissRequest = { createViewModel.showDialog() },
//                    title = { CommonText(text = "Selecciona un tipo de actividad", fontSize = 16) },
//                    text = {
//                        Column {
//                            variables.tipoActividades?.forEach { tipo ->
//                                CommonText(
//                                    text = tipo.descripcion,
//                                    fontSize = 16,
//                                    textAlign = TextAlign.Start,
//                                    modifier = Modifier
//                                        .fillMaxWidth()
//                                        .padding(8.dp)
//                                        .clickable {
//                                            createViewModel.selectedValue(tipo)
//                                            createViewModel.showDialog()
//                                        }
//                                )
//                            }
//                        }
//                    },
//                    confirmButton = {},
//                    dismissButton = {}
//                )
//            }
            ReportContent(variables = variables)
//        }
    }
}

@Composable
private fun ReportContent(variables: ReporteState) {
    Header(nombre = variables.usuario?.nombre ?: "", codigoFamilia = variables.usuario?.codigoFamilia ?: "")
    CommonSpacer(size = 15)
    Users(alumnos = variables.alumnos ?: emptyList())
    CommonSpacer(size = 15)
    PointsDetails()
    CommonSpacer(size = 15)
    CalendarDetails()
}

@Composable
private fun Header(nombre: String?, codigoFamilia: String) {
    CommonText(text = "Bienvenido $nombre", fontSize = 24, fontWeight = FontWeight.Bold)
    CommonSpacer(size = 10)
    CommonCard(
        texto = "Codigo de Familia : $codigoFamilia",
        icono = R.drawable.ic_copy,
        contentDescription = "Copiar Codigo de Familia"
    )
    CommonSpacer(size = 10)

    CommonCard(
        texto = "Mes : Junio",
        icono = R.drawable.ic_arrow_down,
        contentDescription = "Menu Desplegable de Mes"
    )
}

@Composable
private fun Users(alumnos: List<AlumnoEntity>) {
//    CommonText(text = "Solicitudes", fontSize = 20, fontWeight = FontWeight.Bold)
//    CommonSpacer(size = 10)
//
//    val requests = listOf(
//        Triple("Saul", false, 1)
//    )
//
//    LazyColumn(
//        modifier = Modifier,
//        verticalArrangement = Arrangement.spacedBy(10.dp)
//    ) {
//        items(requests) { (tarea, selected, puntaje) ->
//            CommonUserCard(
//                tareas = tarea,
//                selected = selected,
//                puntaje = puntaje,
//                tipo = 2,
//                descripcion2 = "Solicitud de Union"
//            )
//        }
//    }
//    CommonSpacer(size = 20)

    CommonText(text = "Usuarios", fontSize = 20, fontWeight = FontWeight.Bold)
    CommonSpacer(size = 10)

    LazyColumn(
        modifier = Modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(alumnos) { it ->
            CommonUserCard(
                tareas = it.nombre,
                selected = false,
                puntaje = it.idUsuario
            )
        }
    }

}

@Composable
private fun PointsDetails() {
    CommonText(text = "Total de Puntos : 120", fontSize = 20, fontWeight = FontWeight.Bold)
    CommonSpacer(size = 10)
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Column(
            modifier = Modifier
                .weight(1f)
                .clip(shape = RoundedCornerShape(12.dp))
                .background(DarkUnselectedItems)
                .padding(horizontal = 10.dp, vertical = 15.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                CommonText(
                    text = "Puntos",
                    fontSize = 16,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2
                )
                CommonText(
                    text = "Ganados",
                    fontSize = 16,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2
                )
            }
            CommonSpacer(size = 10)
            Row {
                CommonText(
                    text = "7",
                    fontSize = 16,
                    fontWeight = FontWeight.Bold,
                    color = DarkOrange
                )
                CommonSpacer(size = 12)
                CommonIcon(
                    size = 20,
                    icon = R.drawable.ic_coin,
                    contentDescription = "Moneda",
                    tint = DarkOrange
                )
            }
        }
        CommonSpacer(size = 20)
        Column(
            modifier = Modifier
                .weight(1f)
                .clip(shape = RoundedCornerShape(12.dp))
                .background(DarkUnselectedItems)
                .padding(horizontal = 10.dp, vertical = 15.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                CommonText(
                    text = "Puntos",
                    fontSize = 16,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2
                )
                CommonText(
                    text = "Canjeados",
                    fontSize = 16,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2
                )
            }
            CommonSpacer(size = 10)
            Row {
                CommonText(
                    text = "7",
                    fontSize = 16,
                    fontWeight = FontWeight.Bold,
                    color = DarkOrange
                )
                CommonSpacer(size = 12)
                CommonIcon(
                    size = 20,
                    icon = R.drawable.ic_coin,
                    contentDescription = "Moneda",
                    tint = DarkOrange
                )
            }
        }
        CommonSpacer(size = 20)
        Column(
            modifier = Modifier
                .weight(1f)
                .clip(shape = RoundedCornerShape(12.dp))
                .background(DarkUnselectedItems)
                .padding(horizontal = 10.dp, vertical = 15.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                CommonText(
                    text = "Puntos",
                    fontSize = 16,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2
                )
                CommonText(
                    text = "Penalizados",
                    fontSize = 16,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2
                )
            }
            CommonSpacer(size = 10)
            Row {
                CommonText(
                    text = "7",
                    fontSize = 16,
                    fontWeight = FontWeight.Bold,
                    color = DarkOrange
                )
                CommonSpacer(size = 12)
                CommonIcon(
                    size = 20,
                    icon = R.drawable.ic_coin,
                    contentDescription = "Moneda",
                    tint = DarkOrange
                )
            }
        }
    }
}

@Composable
private fun CalendarDetails() {
    Column {

        val fechas = listOf(
            Triple("Dom", true, 2),
            Triple("Lun", false, 3),
            Triple("Mar", false, 3),
            Triple("Dom", true, 2),
            Triple("Lun", false, 3),
            Triple("Mar", false, 3),
            Triple("Dom", true, 2),
            Triple("Lun", false, 3),
            Triple("Mar", false, 3),
        )

        LazyRow (
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(fechas) { (dfecha, selected, fecha) ->
                Column(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(shape = RoundedCornerShape(12.dp))
                        .background(if(selected) DarkSelectedItems else DarkUnselectedItems),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CommonText(
                        text = dfecha,
                        fontSize = 16,
                        fontWeight = FontWeight.Bold
                    )
                    CommonText(
                        text = fecha.toString(),
                        fontSize = 16,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        CommonSpacer(size = 20)

        val users = listOf(
            Triple("Tarea 1", false, 2),
            Triple("Tarea 2", false, 3),
            Triple("Tarea 3", true, 1),
        )

        LazyColumn {
            items(users) { (tarea, selected, puntaje) ->
                CommonTaskCard(
                    tareas = tarea,
                    done = selected,
                    puntaje = puntaje
                )
            }
        }
    }
}
