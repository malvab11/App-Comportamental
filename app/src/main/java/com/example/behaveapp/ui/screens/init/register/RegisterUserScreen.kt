package com.example.behaveapp.ui.screens.init.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.behaveapp.R
import com.example.behaveapp.data.screensNavigation.ScreenNavigation
import com.example.behaveapp.ui.data.init.RegisterState
import com.example.behaveapp.ui.screens.commons.CommonAlertDialog
import com.example.behaveapp.ui.screens.commons.CommonIcon
import com.example.behaveapp.ui.screens.commons.CommonOutlinedButtons
import com.example.behaveapp.ui.screens.commons.CommonSpacer
import com.example.behaveapp.ui.screens.commons.CommonText
import com.example.behaveapp.ui.screens.commons.LoginTextField
import com.example.behaveapp.ui.screens.commons.commonToast
import com.example.behaveapp.ui.theme.BlackEndBackground
import com.example.behaveapp.ui.theme.BlackStartBackground
import com.example.behaveapp.ui.theme.DarkButtons
import com.example.behaveapp.ui.theme.DarkOrange
import com.example.behaveapp.ui.viewModels.initViewModels.RegisterViewModel

@Composable
fun RegisterUserScreem(
    modifier: Modifier = Modifier,
    navController: NavController,
    registerViewModel: RegisterViewModel
) {
    val variables by registerViewModel.variables.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        registerViewModel.resetValues()
    }

    LaunchedEffect(variables.registerResponse) {
        val response = variables.registerResponse
        if (response != null) {
            commonToast(context, response.message)
            if (response.status == "success") {
                navController.navigate(
                    ScreenNavigation.HomeScreen.crearRuta(
                        idUsuario = variables.registerResponse?.idUsuario ?: 0,
                        tipoUsuario = variables.registerResponse?.tipoUsuario ?: 0
                    )
                ) {
                    popUpTo(0)
                }
                registerViewModel.resetValues()
            }
        }
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
            if (variables.isLoading) {
                CommonAlertDialog(text = "Ingresando a la familia", fontSize = 16) { }
            }

            Header(
                onReturn = {
                    navController.popBackStack()
                    registerViewModel.resetValues()
                }
            )

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                RegisterUserContent(
                    variables = variables,
                    onValueChange = { nombres, codigoFamilia ->
                        registerViewModel.onValueChangeRegisterUser(
                            nombre = nombres,
                            codigoFamilia = codigoFamilia
                        )
                    })
            }

            CommonOutlinedButtons(
                modifier = Modifier.fillMaxWidth(),
                texto = "Unirme a la familia",
                containterColor = DarkButtons,
                enabled = variables.isEnabled,
                tamanoTexto = 16
            ) {
                registerViewModel.validateRegisterUsuario()
            }
        }
    }
}

@Composable
private fun Header(onReturn: () -> Unit) {
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
            onReturn()
        }
    }
}

@Composable
private fun RegisterUserContent(variables: RegisterState, onValueChange: (String, String) -> Unit) {

    CommonText(
        modifier = Modifier.fillMaxWidth(),
        text = "Inicia Sesión con tu código de invitación",
        fontWeight = FontWeight.Bold,
        fontSize = 32,
        textAlign = TextAlign.Start,
        lineHeight = 35.sp,
        maxLines = 2
    )

    CommonSpacer(size = 12)

    CommonText(
        modifier = Modifier.fillMaxWidth(),
        text = "Solicita a tu tutor el código de invitación para poder unirte a la familia",
        fontSize = 20,
        textAlign = TextAlign.Start,
        lineHeight = 25.sp,
        maxLines = 2
    )

    CommonSpacer(size = 12)

    LoginTextField(
        "Nombres y Apellidos",
        value = variables.nombres,
        onValueChange = {
            onValueChange(it, variables.codigoFamilia)
        })
    LoginTextField(
        "Código de Invitación",
        value = variables.codigoFamilia,
        keyboardType = KeyboardType.Number,
        onValueChange = {
            onValueChange(variables.nombres, it)
        })
}

