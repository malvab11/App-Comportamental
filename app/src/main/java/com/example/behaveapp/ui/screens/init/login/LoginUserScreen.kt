package com.example.behaveapp.ui.screens.init.login

import android.widget.Toast
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.behaveapp.R
import com.example.behaveapp.data.screensNavigation.screensNavigation
import com.example.behaveapp.ui.screens.commons.CommonIcon
import com.example.behaveapp.ui.screens.commons.CommonOutlinedButtons
import com.example.behaveapp.ui.screens.commons.CommonSpacer
import com.example.behaveapp.ui.screens.commons.CommonText
import com.example.behaveapp.ui.screens.commons.LoginTextField
import com.example.behaveapp.ui.screens.viewModels.initViewModels.LoginViewModel
import com.example.behaveapp.ui.theme.BlackEndBackground
import com.example.behaveapp.ui.theme.BlackStartBackground
import com.example.behaveapp.ui.theme.DarkButtons
import com.example.behaveapp.ui.theme.DarkOrange

@Composable
fun LoginUserScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    loginViewModel: LoginViewModel
) {

    val enabled by loginViewModel.isEnabled.observeAsState(false)
    val context = LocalContext.current
    val registerUserResponse by loginViewModel.registerResponse.observeAsState()
    val mensajeError by loginViewModel.mensajeError.observeAsState()

    // Observa Login Exitoso
    LaunchedEffect(registerUserResponse) {
        registerUserResponse?.let {
            Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
            loginViewModel.clearMensajeError()
            navController.navigate(screensNavigation.LoadingScreen.ruta) {
                popUpTo(navController.graph.startDestinationId) { inclusive = true }
            }
        }
    }

    // Observa Errores
    LaunchedEffect(mensajeError) {
        mensajeError?.takeIf { it.isNotBlank() }?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            loginViewModel.clearMensajeError()
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
            Header(navController = navController)

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LoginUserContent(loginViewModel = loginViewModel)
            }

            CommonOutlinedButtons(
                modifier = Modifier.fillMaxWidth(),
                texto = "Unirme a la familia",
                containterColor = DarkButtons,
                enabled = enabled,
                tamanoTexto = 16
            ) {
                loginViewModel.validateRegisterUser()
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
private fun LoginUserContent(loginViewModel: LoginViewModel) {

    val nombres by loginViewModel.nombres.observeAsState("")
    val codigoFamilia by loginViewModel.codigoFamilia.observeAsState("")

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
    LoginTextField("Nombres y Apellidos",value = nombres, onValueChange = {loginViewModel.onValueChangeRegisterUser(nombre = it, codigoFamilia = codigoFamilia)})

    CommonSpacer(size = 12)
    LoginTextField("Código de Invitación",value = codigoFamilia, onValueChange = {loginViewModel.onValueChangeRegisterUser(nombre = nombres, codigoFamilia = it)})
}

