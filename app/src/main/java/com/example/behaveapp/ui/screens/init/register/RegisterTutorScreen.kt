package com.example.behaveapp.ui.screens.init.register

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
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
import com.example.behaveapp.ui.theme.DarkOrange

@Composable
fun RegisterTutorScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    loginViewModel: LoginViewModel
) {
    Scaffold { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Brush.linearGradient(listOf(BlackStartBackground, BlackEndBackground)))
                .padding(
                    top = innerPadding.calculateTopPadding(),
                    bottom = innerPadding.calculateBottomPadding(),
                    start = 12.dp,
                    end = 12.dp
                )
        ) {
            CommonIcon(
                size = 24,
                icon = R.drawable.ic_arrow_back,
                contentDescription = "Retroceder",
                tint = DarkOrange
            ) {
                navController.popBackStack()
            }

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                RegisterContent(navController, loginViewModel)
            }
        }
    }
}


@Composable
private fun RegisterContent(navController: NavController, loginViewModel: LoginViewModel) {
    val usuario by loginViewModel.usuario.observeAsState("")
    val nombres by loginViewModel.nombres.observeAsState("")
    val contrasena by loginViewModel.contrasena.observeAsState("")
    val repeatContrasena by loginViewModel.repeatContrasena.observeAsState("")
    val isShown by loginViewModel.isShown.observeAsState(false)
    val enabled by loginViewModel.isEnabled.observeAsState(false)
    val errorContrasena by loginViewModel.errorContrasena.observeAsState()
    val mensajeError by loginViewModel.mensajeError.observeAsState()
    val context = LocalContext.current
    val registerResponse by loginViewModel.registerResponse.observeAsState()

    // Mostrar mensajes
    registerResponse?.let {
        Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
        loginViewModel.clearMensajeError()
        navController.navigate(screensNavigation.LoadingScreen.ruta) {
            popUpTo(navController.graph.startDestinationId) { inclusive = true }
        }
    }

    mensajeError?.let {
        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        loginViewModel.clearMensajeError()
    }

    CommonSpacer(size = 12)
    CommonText(text = "Registrarme", modifier = Modifier.fillMaxWidth(), fontWeight = FontWeight.Bold, fontSize = 32)
    CommonSpacer(size = 12)

    fun updateFields(newUsuario: String = usuario, newNombre: String = nombres, newPass: String = contrasena, newRepeat: String = repeatContrasena) {
        loginViewModel.onValueChangeRegister(newUsuario, newNombre, newPass, newRepeat)
    }

    LoginTextField("Email o Celular", value = usuario) { updateFields(newUsuario = it) }
    CommonSpacer(size = 12)
    LoginTextField("Nombres y Apellidos", value = nombres) { updateFields(newNombre = it) }
    CommonSpacer(size = 12)
    LoginTextField(
        "Contraseña",
        value = contrasena,
        isPassword = true,
        isShown = isShown,
        isError = errorContrasena != null,
        errorMessage = errorContrasena,
        onClick = { loginViewModel.showPassword() }
    ) { updateFields(newPass = it) }

    CommonSpacer(size = 12)
    LoginTextField(
        "Repetir Contraseña",
        value = repeatContrasena,
        isPassword = true,
        isShown = isShown,
        isError = errorContrasena != null,
        errorMessage = errorContrasena,
        onClick = { loginViewModel.showPassword() }
    ) { updateFields(newRepeat = it) }

    CommonSpacer(size = 20)
    CommonOutlinedButtons(
        modifier = Modifier.fillMaxWidth(),
        texto = "Registrarme",
        containterColor = DarkOrange,
        tamanoTexto = 16,
        enabled = enabled
    ) {
        loginViewModel.validateRegister()
    }

    CommonSpacer(size = 12)
    CommonText(text = "o", fontWeight = FontWeight.Bold, fontSize = 15)
    CommonSpacer(size = 12)

    Row(Modifier.fillMaxWidth()) {
        CommonOutlinedButtons(
            modifier = Modifier.weight(1f).padding(end = 6.dp),
            texto = "Google",
            containterColor = Color.White,
            colorTexto = Color.Black,
            icon = R.drawable.ic_google,
            tamanoTexto = 16
        ) {}

        CommonOutlinedButtons(
            modifier = Modifier.weight(1f).padding(start = 6.dp),
            texto = "Facebook",
            containterColor = Color(0xFF2B57E8),
            icon = R.drawable.ic_facebook,
            tamanoTexto = 16
        ) {}
    }
}