package com.example.behaveapp.ui.screens.init.login

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.behaveapp.R
import com.example.behaveapp.data.screensNavigation.ScreenNavigation
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
import com.example.behaveapp.ui.viewModels.initViewModels.LoginViewModel

@Composable
fun LoginTutorScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    loginViewModel: LoginViewModel
) {
    val variables by loginViewModel.variables.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        loginViewModel.resetValues()
    }

    LaunchedEffect(variables.loginResponse) {
        val response = variables.loginResponse
        if (response != null) {
            commonToast(context, response.message)
            if (response.status == "success") {
                navController.navigate(
                    ScreenNavigation.LoadingScreen.ruta
                ) {
                    popUpTo(0)
                }
                loginViewModel.resetValues()
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
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                CommonIcon(
                    size = 24,
                    icon = R.drawable.ic_arrow_back,
                    contentDescription = "Retroceder",
                    tint = DarkOrange
                ) {
                    navController.popBackStack()
                    loginViewModel.resetValues()
                }
            }
            if (variables.isLoading) {
                CommonAlertDialog(text = "Validando Usuario", fontSize = 16) { }
            }
            Column(
                modifier = modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                LoginContent(
                    navController = navController,
                    loginViewModel = loginViewModel,
                    usuario = variables.usuario,
                    contrasena = variables.contrasena,
                    isShown = variables.isShown,
                    enabled = variables.isEnabled
                )
            }

        }
    }
}

@Composable
private fun LoginContent(
    navController: NavController,
    loginViewModel: LoginViewModel,
    usuario: String,
    contrasena: String,
    isShown: Boolean,
    enabled: Boolean
) {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        CommonSpacer(size = 12)
        CommonText(
            modifier = Modifier.fillMaxWidth(),
            text = "Iniciar Sesión",
            fontWeight = FontWeight.Bold,
            fontSize = 32
        )

        CommonSpacer(size = 12)
        LoginTextField(
            placeholder = "Email o Celular",
            value = usuario,
            onValueChange = { loginViewModel.onValueChange(it, contrasena) }
        )
        LoginTextField(
            placeholder = "Contraseña",
            isPassword = true,
            value = contrasena,
            isShown = isShown,
            keyboardType = KeyboardType.Password,
            onClick = { loginViewModel.showPassword() },
            onValueChange = { loginViewModel.onValueChange(usuario, it) }
        )
        CommonText(
            modifier = Modifier.fillMaxWidth(),
            text = "Olvidaste Contraseña",
            color = DarkOrange,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.End,
            fontSize = 12
        )

        CommonSpacer(size = 20)
        CommonOutlinedButtons(
            modifier = Modifier.fillMaxWidth(),
            texto = "Iniciar Sesión",
            containterColor = DarkButtons,
            tamanoTexto = 16,
            enabled = enabled
        ) {
            loginViewModel.validateLogin()
        }

        CommonSpacer(size = 12)
        CommonOutlinedButtons(
            modifier = Modifier.fillMaxWidth(),
            texto = "Registrarme",
            containterColor = DarkOrange,
            tamanoTexto = 16
        ) {
            navController.navigate(ScreenNavigation.RegisterTutorScreen.ruta)
        }

        CommonSpacer(size = 12)
        CommonText(text = "o", fontWeight = FontWeight.Bold, fontSize = 15)
        CommonSpacer(size = 12)

        Row(modifier = Modifier.fillMaxWidth()) {
            CommonOutlinedButtons(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 6.dp),
                texto = "Google",
                containterColor = Color.White,
                colorTexto = Color.Black,
                icon = R.drawable.ic_google,
                tamanoTexto = 16
            ) {}

            CommonOutlinedButtons(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 6.dp),
                texto = "Facebook",
                containterColor = Color(0xFF2B57E8),
                icon = R.drawable.ic_facebook,
                tamanoTexto = 16
            ) {}
        }
    }
}

