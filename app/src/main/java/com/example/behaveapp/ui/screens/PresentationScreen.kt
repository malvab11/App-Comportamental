package com.example.behaveapp.ui.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.behaveapp.ui.screens.commons.CommonOutlinedButtons
import com.example.behaveapp.ui.screens.commons.CommonSpacer
import com.example.behaveapp.ui.screens.commons.CommonTaskCard
import com.example.behaveapp.ui.screens.commons.CommonText
import com.example.behaveapp.ui.screens.commons.CommonUserCard
import com.example.behaveapp.ui.theme.BlackEndBackground
import com.example.behaveapp.ui.theme.BlackStartBackground
import com.example.behaveapp.ui.theme.DarkButtons
import com.example.behaveapp.ui.theme.DarkSelectedItems
import com.example.behaveapp.ui.theme.DarkUnselectedItems
import kotlinx.coroutines.delay

@Composable
fun PresentationScreen(modifier: Modifier = Modifier) {

    Scaffold(
        topBar = { /* ... */ },
        bottomBar = { /* ... */ }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(
                    Brush.linearGradient(
                        listOf(BlackStartBackground, BlackEndBackground)
                    )
                )
                .padding(innerPadding) // Aquí aplicas el padding interno
        ) {
            Carrousel(modifier = Modifier.weight(1f))
            Footer()
        }
    }
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun Carrousel(modifier: Modifier) {
    val presentations = listOf<@Composable (Modifier) -> Unit>(
        { PrimeraPresentacion(it) },
        { SegundaPresentacion(it) },
        { TerceraPresentacion(it) }
    )

    var currentIndex by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(5000)
            currentIndex = (currentIndex + 1) % presentations.size
        }
    }

    Column(modifier = modifier) {
        AnimatedContent(
            targetState = currentIndex,
            transitionSpec = {
                slideInHorizontally(animationSpec = tween(500)) { fullWidth -> fullWidth } + fadeIn() with
                        slideOutHorizontally(animationSpec = tween(500)) { fullWidth -> -fullWidth } + fadeOut()
            },
            modifier = Modifier.weight(1f)
        ) { index ->
            presentations[index](Modifier.fillMaxSize())
        }

        // Indicador visual (círculos)
        CarouselIndicator(
            total = presentations.size,
            currentIndex = currentIndex,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp, horizontal = 10.dp)
        )
    }
}

@Composable
private fun CarouselIndicator(
    total: Int,
    currentIndex: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(total) { index ->
            Box(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .size(if (index == currentIndex) 12.dp else 8.dp)
                    .clip(CircleShape)
                    .background(if (index == currentIndex) DarkButtons else BlackStartBackground)
            )
        }
    }
}


@Composable
private fun PrimeraPresentacion(modifier: Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CommonText(text = "Pequeñas tareas", fontSize = 35, fontWeight = FontWeight.Bold)
        CommonText(text = "Grandes hábitos", fontSize = 35, fontWeight = FontWeight.Bold)
        CommonSpacer(size = 50)
        val tasks = listOf(
            Triple("Hacer la cama", false, 2),
            Triple("Participar en clase", false, 3),
            Triple("Limpiar el cuarto", true, 1)
        )

        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(tasks) { (tarea, done, puntaje) ->
                CommonTaskCard(
                    tareas = tarea,
                    done = done,
                    puntaje = puntaje
                )
            }
        }
    }
}

@Composable
private fun SegundaPresentacion(modifier: Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CommonText(modifier = Modifier.padding(horizontal = 8.dp),text = "Motiva con recompensas", fontSize = 35, fontWeight = FontWeight.Bold, maxLines = 2)
        CommonSpacer(size = 50)
        val tasks = listOf(
            Triple("Noche de Cine", false, 2),
            Triple("Comprar Lego", true, 3),
            Triple("Noche de Pizza", false, 1),
            Triple("Salida a Jugar", true, 1)
        )

        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(tasks) { (tarea, done, puntaje) ->
                CommonTaskCard(
                    tareas = tarea,
                    done = done,
                    puntaje = puntaje
                )
            }
        }
    }
}

@Composable
private fun TerceraPresentacion(modifier: Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CommonText(modifier = Modifier.padding(horizontal = 8.dp),text = "Motiva con recompensas", fontSize = 35, fontWeight = FontWeight.Bold, maxLines = 2)
        CommonSpacer(size = 50)
        val tasks = listOf(
            Triple("Noche de Cine", false, 2),
            Triple("Comprar Lego", true, 3),
            Triple("Noche de Pizza", false, 1),
            Triple("Salida a Jugar", true, 1)
        )

        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(tasks) { (tarea, selected, puntaje) ->
                CommonUserCard(
                    tareas = tarea,
                    selected = selected,
                    puntaje = puntaje
                )
            }
        }
    }
}

@Composable
private fun Footer() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
            .background(DarkUnselectedItems),

        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CommonOutlinedButtons(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 12.dp, vertical = 20.dp),
            texto = "Soy Tutor",
            containterColor = DarkButtons,
            tamanoTexto = 16
        ) { }
        CommonOutlinedButtons(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 12.dp, vertical = 20.dp),
            texto = "Soy Alumno",
            containterColor = DarkSelectedItems,
            tamanoTexto = 16
        ) { }

    }
}
