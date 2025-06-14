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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.behaveapp.R
import com.example.behaveapp.data.screensNavigation.ScreenNavigation
import com.example.behaveapp.ui.data.RegisterState
import com.example.behaveapp.ui.screens.commons.CommonAlertDialog
import com.example.behaveapp.ui.screens.commons.CommonIcon
import com.example.behaveapp.ui.screens.commons.CommonOutlinedButtons
import com.example.behaveapp.ui.screens.commons.CommonSpacer
import com.example.behaveapp.ui.screens.commons.CommonText
import com.example.behaveapp.ui.screens.commons.LoginTextField
import com.example.behaveapp.ui.screens.commons.commonToast
import com.example.behaveapp.ui.theme.BlackEndBackground
import com.example.behaveapp.ui.theme.BlackStartBackground
import com.example.behaveapp.ui.theme.DarkOrange
import com.example.behaveapp.ui.viewModels.initViewModels.RegisterViewModel

@Composable
fun RegisterTutorScreen(
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
        if (response != null){
            commonToast(context,response.message)
            if (response.status == "success"){
                navController.popBackStack()
                registerViewModel.resetValues()
            }
        }
    }

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
                registerViewModel.resetValues()
            }

            if (variables.isLoading) {
                CommonAlertDialog(text = "Registrando Usuario", fontSize = 16) { }
            }

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                RegisterContent(
                    variables = variables,
                    onEvent = { correo, celular, nombre, contrasena, repetirContrasena ->
                        registerViewModel.onValueChangeRegister(
                            correo,
                            celular,
                            nombre,
                            contrasena,
                            repetirContrasena
                        )
                    },
                    onClick = {
                        registerViewModel.showPassword()
                    },
                    onRegister = {
                        registerViewModel.validateRegisterTutor()
                    }
                )
            }
        }
    }
}


@Composable
private fun RegisterContent(
    variables: RegisterState,
    onEvent: (String, String, String, String, String) -> Unit,
    onClick: () -> Unit,
    onRegister: () -> Unit
) {

    CommonSpacer(size = 12)
    CommonText(
        text = "Registrarme",
        modifier = Modifier.fillMaxWidth(),
        fontWeight = FontWeight.Bold,
        fontSize = 32
    )
    CommonSpacer(size = 12)

    LoginTextField("Email", value = variables.correo, keyboardType = KeyboardType.Email) {
        onEvent(
            it,
            variables.celular,
            variables.nombres,
            variables.contrasena,
            variables.contrasenaRepetida
        )
    }
    LoginTextField("Celular", value = variables.celular, keyboardType = KeyboardType.Number) {
        onEvent(
            variables.correo,
            it,
            variables.nombres,
            variables.contrasena,
            variables.contrasenaRepetida
        )
    }
    LoginTextField(
        "Nombres y Apellidos",
        value = variables.nombres,
        keyboardType = KeyboardType.Text
    ) {
        onEvent(
            variables.correo,
            variables.celular,
            it,
            variables.contrasena,
            variables.contrasenaRepetida
        )
    }
    LoginTextField(
        "Contraseña",
        value = variables.contrasena,
        isPassword = true,
        isShown = variables.isShown,
        isError = variables.errorContrasena != null,
        keyboardType = KeyboardType.Password,
        errorMessage = variables.errorContrasena,
        onClick = { onClick() }
    ) {
        onEvent(
            variables.correo,
            variables.celular,
            variables.nombres,
            it,
            variables.contrasenaRepetida
        )
    }

    LoginTextField(
        "Repetir Contraseña",
        value = variables.contrasenaRepetida,
        isPassword = true,
        isShown = variables.isShown,
        isError = variables.errorContrasena != null,
        errorMessage = variables.errorContrasena,
        keyboardType = KeyboardType.Password,
        onClick = { onClick() }
    ) {
        onEvent(variables.correo, variables.celular, variables.nombres, variables.contrasena, it)
    }

    CommonSpacer(size = 20)
    CommonOutlinedButtons(
        modifier = Modifier.fillMaxWidth(),
        texto = "Registrarme",
        containterColor = DarkOrange,
        tamanoTexto = 16,
        enabled = variables.isEnabled
    ) {
        onRegister()
    }

    CommonSpacer(size = 12)
    CommonText(text = "o", fontWeight = FontWeight.Bold, fontSize = 15)
    CommonSpacer(size = 12)

    Row(Modifier.fillMaxWidth()) {
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