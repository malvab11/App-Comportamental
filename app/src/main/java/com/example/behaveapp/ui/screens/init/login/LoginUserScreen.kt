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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
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
import com.example.behaveapp.ui.theme.BlackEndBackground
import com.example.behaveapp.ui.theme.BlackStartBackground
import com.example.behaveapp.ui.theme.DarkButtons
import com.example.behaveapp.ui.theme.DarkOrange

@Composable
fun LoginUserScreen(modifier: Modifier = Modifier, navController: NavController) {
    Scaffold(
        topBar = { /* Puedes agregar una TopBar aquí */ },
        bottomBar = { /* Puedes agregar una BottomBar aquí */ }
    ) { innerPadding ->
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
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                CommonIcon(
                    size = 24,
                    icon = R.drawable.ic_arrow_back,
                    contentDescription = "Retroceder",
                    tint = DarkOrange
                ) { navController.popBackStack() }
            }
            Column(
                modifier = modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                LoginUserContent()
            }
            CommonOutlinedButtons(
                modifier = Modifier.fillMaxWidth(),
                texto = "Unirme a la familia",
                containterColor = DarkButtons,
                tamanoTexto = 16
            ) {
                navController.navigate(screensNavigation.LoadingScreen.ruta) {
                    popUpTo(navController.graph.startDestinationId) { inclusive = true }
                }
            }
        }
    }
}

@Composable
private fun LoginUserContent() {

    CommonText(
        modifier = Modifier.fillMaxWidth(),
        text = "Inicia Sesión con tu codigo de invitación",
        fontWeight = FontWeight.Bold,
        fontSize = 32,
        textAlign = TextAlign.Start,
        lineHeight = 35.sp,
        maxLines = 2
    )

    CommonSpacer(size = 12)
    CommonText(
        modifier = Modifier.fillMaxWidth(),
        text = "Solicita a tu tutor el codigo de invitación para poder unirte a la familia",
        fontSize = 20,
        textAlign = TextAlign.Start,
        lineHeight = 25.sp,
        maxLines = 2
    )

    CommonSpacer(size = 12)
    LoginTextField("Nombres y Apellidos")

    CommonSpacer(size = 12)
    LoginTextField("Codigo de Invitación")

}
