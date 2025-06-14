package com.example.behaveapp.ui.screens.home.createActivity

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.behaveapp.R
import com.example.behaveapp.data.models.TipoActividades
import com.example.behaveapp.ui.screens.commons.CommonCard
import com.example.behaveapp.ui.screens.commons.CommonIcon
import com.example.behaveapp.ui.screens.commons.CommonOutlinedButtons
import com.example.behaveapp.ui.screens.commons.CommonSpacer
import com.example.behaveapp.ui.screens.commons.CommonText
import com.example.behaveapp.ui.screens.commons.LoginTextField
import com.example.behaveapp.ui.viewModels.HomeViewModel
import com.example.behaveapp.ui.theme.BlackEndBackground
import com.example.behaveapp.ui.theme.BlackStartBackground
import com.example.behaveapp.ui.theme.DarkButtons
import com.example.behaveapp.ui.theme.DarkOrange
import com.example.behaveapp.ui.theme.DarkUnselectedItems

@Composable
fun CreateActivityScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel,
    navController: NavHostController,
    idActividad: Int
) {

    val showDialog = remember { mutableStateOf(false) }
    val enabled by homeViewModel.isEnabled.observeAsState(false)

    val tipoSeleccionado by homeViewModel.tipoActividadSeleccionado.observeAsState(TipoActividades(id = 0, descripcion = "Seleccione el Tipo de Actividad", detalle = ""))

    val tipoActividad by homeViewModel.tipoActividades.observeAsState()
    val tituloActividad by homeViewModel.tituloActividad.observeAsState("")
    val puntajeActividad by homeViewModel.puntaje.observeAsState("")

    Scaffold { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(
                    Brush.linearGradient(listOf(BlackStartBackground, BlackEndBackground))
                )
                .padding(
                    top = innerPadding.calculateTopPadding(),
                    bottom = innerPadding.calculateBottomPadding(),
                    start = 12.dp,
                    end = 12.dp
                ),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            if (showDialog.value) {
                AlertDialog(
                    onDismissRequest = { showDialog.value = false },
                    title = { CommonText(text = "Selecciona un tipo de actividad", fontSize = 16) },
                    text = {
                        Column {
                            tipoActividad?.tipoActividades?.forEach { tipo ->
                                CommonText(
                                    text = tipo.descripcion,
                                    fontSize = 16,
                                    textAlign = TextAlign.Start,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp)
                                        .clickable {
                                            homeViewModel.onTipoActividadSeleccionado(tipo)
                                            homeViewModel.onValueChange(
                                                tituloActividad = tituloActividad,
                                                puntaje = puntajeActividad
                                            )
                                            showDialog.value = false
                                        }
                                )
                            }
                        }
                    },
                    confirmButton = {},
                    dismissButton = {}
                )
            }

            Header(navController = navController)

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CreateContent(
                    homeViewModel = homeViewModel,
                    showDialog = showDialog,
                    tipoSeleccionado = tipoSeleccionado.descripcion,
                    tituloActividad = tituloActividad,
                    puntajeActividad = puntajeActividad
                )
            }

            if (idActividad > 0) {
                CommonOutlinedButtons(
                    modifier = Modifier.fillMaxWidth(),
                    texto = "Guardar Actividad",
                    containterColor = DarkButtons,
                    enabled = enabled,
                    tamanoTexto = 16
                ) {
                    homeViewModel.editActivities(
                        idActividad = idActividad,
                        tituloActividad = tituloActividad,
                        puntaje = puntajeActividad
                    )
                    navController.popBackStack()
                }
                CommonOutlinedButtons(
                    modifier = Modifier.fillMaxWidth(),
                    texto = "Eliminar Actividad",
                    containterColor = Color.Red,
                    tamanoTexto = 16
                ) {
                    homeViewModel.editActivities(
                        idActividad = idActividad,
                        tituloActividad = tituloActividad,
                        puntaje = puntajeActividad,
                        eliminado = 1
                    )
                    navController.popBackStack()
                }
            }
            if (idActividad == 0) {
                CommonOutlinedButtons(
                    modifier = Modifier.fillMaxWidth(),
                    texto = "Guardar Actividad",
                    containterColor = DarkButtons,
                    enabled = enabled,
                    tamanoTexto = 16
                ) {
                    homeViewModel.saveActivities(
                        tituloActividad = tituloActividad,
                        puntaje = puntajeActividad
                    )
                    navController.popBackStack()
                }
            }
        }
    }
}

@Composable
private fun Header(navController: NavController) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
        CommonIcon(
            size = 24,
            icon = R.drawable.ic_arrow_back,
            contentDescription = "Retroceder",
            tint = DarkOrange
        ) {
            navController.popBackStack()
        }
    }
}

@Composable
private fun CreateContent(
    homeViewModel: HomeViewModel,
    showDialog: MutableState<Boolean>,
    tituloActividad: String,
    puntajeActividad: String,
    tipoSeleccionado: String,
) {

    CommonText(
        modifier = Modifier.fillMaxWidth(),
        text = "Crear / Editar Actividad",
        fontWeight = FontWeight.Bold,
        fontSize = 32,
        textAlign = TextAlign.Start,
        lineHeight = 35.sp,
        maxLines = 2
    )

    CommonSpacer(size = 12)
    CommonCard(
        modifier = Modifier.clickable { showDialog.value = true },
        texto = tipoSeleccionado,
        icono = R.drawable.ic_arrow_down,
        contentDescription = "Menu desplegable de Tipo de Actividad"
    )

    LoginTextField(
        "Titulo de Actividad",
        value = tituloActividad,
        onValueChange = {
            homeViewModel.onValueChange(
                tituloActividad = it,
                puntaje = puntajeActividad
            )
        },
        maxLines = 4
    )

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = puntajeActividad,
        onValueChange = {
            homeViewModel.onValueChange(
                tituloActividad = tituloActividad,
                puntaje = it
            )
        },
        label = {
            CommonText(
                modifier = Modifier,
                text = "Puntaje",
                fontSize = 16
            )
        },
        trailingIcon = {
            CommonIcon(
                size = 20,
                icon = R.drawable.ic_coin,
                contentDescription = "Moneda",
                tint = DarkOrange
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        maxLines = 1,
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = DarkUnselectedItems,
            focusedContainerColor = DarkUnselectedItems,
            unfocusedBorderColor = Color.Transparent,
            focusedBorderColor = Color.Transparent
        )
    )
}