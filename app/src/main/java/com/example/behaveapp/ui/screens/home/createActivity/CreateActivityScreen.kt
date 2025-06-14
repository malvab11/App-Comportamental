package com.example.behaveapp.ui.screens.home.createActivity

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.behaveapp.R
import com.example.behaveapp.data.models.Actividades
import com.example.behaveapp.data.models.TipoActividades
import com.example.behaveapp.ui.screens.commons.CommonCard
import com.example.behaveapp.ui.screens.commons.CommonIcon
import com.example.behaveapp.ui.screens.commons.CommonOutlinedButtons
import com.example.behaveapp.ui.screens.commons.CommonSpacer
import com.example.behaveapp.ui.screens.commons.CommonText
import com.example.behaveapp.ui.screens.commons.LoginTextField
import com.example.behaveapp.ui.theme.BlackEndBackground
import com.example.behaveapp.ui.theme.BlackStartBackground
import com.example.behaveapp.ui.theme.DarkButtons
import com.example.behaveapp.ui.theme.DarkOrange
import com.example.behaveapp.ui.theme.DarkUnselectedItems
import com.example.behaveapp.ui.viewModels.homeViewModels.CreateViewModel

@Composable
fun CreateActivityScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    createViewModel: CreateViewModel,
) {

    val variables by createViewModel.variables.collectAsState()
    val idUsuario = navController.previousBackStackEntry?.savedStateHandle?.get<Int>("idUsuario")

    LaunchedEffect(Unit) {
        val tipoActividades =
            navController.previousBackStackEntry?.savedStateHandle?.get<List<TipoActividades>>("tipoActividades")
        val actividad =
            navController.previousBackStackEntry?.savedStateHandle?.get<Actividades>("actividad")
        createViewModel.setValues(tipoActividades = tipoActividades, actividad = actividad)
    }

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

            if (variables.isShownAlertDialog) {
                AlertDialog(
                    onDismissRequest = { createViewModel.showDialog() },
                    title = { CommonText(text = "Selecciona un tipo de actividad", fontSize = 16) },
                    text = {
                        Column {
                            variables.tipoActividades?.forEach { tipo ->
                                CommonText(
                                    text = tipo.descripcion,
                                    fontSize = 16,
                                    textAlign = TextAlign.Start,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp)
                                        .clickable {
                                            createViewModel.selectedValue(tipo)
                                            createViewModel.showDialog()
                                        }
                                )
                            }
                        }
                    },
                    confirmButton = {},
                    dismissButton = {}
                )
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CreateContent(
                    onValueChange = { tituloActividad, puntaje ->
                        createViewModel.onValueChange(
                            tituloActividad = tituloActividad,
                            puntaje = puntaje
                        )
                    },
                    onClick = { createViewModel.showDialog() },
                    tipoSeleccionado = variables.tipoActividadSeleccionado?.descripcion
                        ?: "Tipo Actividad",
                    tituloActividad = variables.tituloActividad,
                    puntajeActividad = variables.puntaje
                )
            }

            CommonOutlinedButtons(
                modifier = Modifier.fillMaxWidth(),
                texto = "Guardar Actividad",
                containterColor = DarkButtons,
                enabled = variables.isEnabled,
                tamanoTexto = 16
            ) {
                if (variables.actividad != null) {
                    createViewModel.editActivities()
                    navController.popBackStack()
                } else {
                    createViewModel.saveActivities(idUsuario = idUsuario!!)
                    navController.popBackStack()
                }
            }

        }
    }
}

@Composable
private fun CreateContent(
    onValueChange: (String, String) -> Unit,
    onClick: () -> Unit,
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
        modifier = Modifier.clickable { onClick() },
        texto = tipoSeleccionado,
        icono = R.drawable.ic_arrow_down,
        contentDescription = "Menu desplegable de Tipo de Actividad"
    )

    LoginTextField(
        "Titulo de Actividad",
        value = tituloActividad,
        onValueChange = {
            onValueChange(
                it,
                puntajeActividad
            )
        },
        maxLines = 4
    )

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = puntajeActividad,
        onValueChange = {
            onValueChange(
                tituloActividad,
                it
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