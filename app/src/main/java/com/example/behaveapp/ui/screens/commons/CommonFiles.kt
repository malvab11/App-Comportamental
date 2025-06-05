package com.example.behaveapp.ui.screens.commons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.behaveapp.R
import com.example.behaveapp.ui.theme.BlackStartBackground
import com.example.behaveapp.ui.theme.DarkButtons
import com.example.behaveapp.ui.theme.DarkOrange
import com.example.behaveapp.ui.theme.DarkSelectedItems
import com.example.behaveapp.ui.theme.DarkUnselectedItems

@Composable
fun CommonText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.White,
    fontWeight: FontWeight = FontWeight.Normal,
    fontSize: Int,
    textAlign: TextAlign = TextAlign.Center,
    maxLines: Int = 1
) {
    Text(
        text = text,
        color = color,
        fontWeight = fontWeight,
        fontSize = fontSize.sp,
        modifier = modifier,
        textAlign = textAlign,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun CommonIcon(
    size: Int,
    icon: Int,
    contentDescription: String,
    tint: Color
) {
    Icon(
        modifier = Modifier.size(size.dp),
        painter = painterResource(icon),
        contentDescription = contentDescription,
        tint = tint
    )
}

@Composable
fun CommonImage(modifier: Modifier = Modifier, imagen: Int, contentDescription: String) {
    Image(
        modifier = modifier,
        painter = painterResource(imagen),
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop
    )
}

@Composable
fun CommonOutlinedButtons(
    modifier: Modifier = Modifier,
    texto: String,
    containterColor: Color,
    borderColor: Color = Color.Transparent,
    tamanoTexto: Int,
    colorTexto: Color = Color.White,
    icon: Int = 0,
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.outlinedButtonColors(containerColor = containterColor),
        border = BorderStroke(1.dp, borderColor),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (icon != 0) {
                CommonImage(modifier = Modifier.size(30.dp),imagen = icon, contentDescription = "")
                CommonSpacer(size = 5)
            }
            CommonText(
                modifier = Modifier.padding(8.dp),
                text = texto,
                fontWeight = FontWeight.Bold,
                fontSize = tamanoTexto,
                color = colorTexto
            )
        }
    }
}

@Composable
fun CommonCircularProgress(modifier: Modifier) {
    CircularProgressIndicator(
        color = Color.White,
        strokeWidth = 5.dp,
        modifier = modifier
    )
}

@Composable
fun CommonSpacer(modifier: Modifier = Modifier, size: Int) {
    Spacer(modifier.size(size.dp))
}

@Composable
fun LoginTextField(
    placeholder: String,
    isPassword: Boolean = false
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = "",
        onValueChange = {},
        label = {
            CommonText(
                modifier = Modifier,
                text = placeholder,
                fontSize = 12
            )
        },
        trailingIcon = {
            if (isPassword) {
                IconButton(onClick = {}) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_visibility),
                        contentDescription = "Mostrar Contraseña"
                    )
                }
            }
        },
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = DarkUnselectedItems,
            focusedContainerColor = DarkUnselectedItems,
            unfocusedBorderColor = Color.Transparent,
            focusedBorderColor = Color.Transparent
        )
    )
}

@Composable
fun CommonTaskCard(modifier: Modifier = Modifier, done: Boolean, tareas: String, puntaje: Int) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
            modifier = Modifier.size(40.dp),
            shape = RoundedCornerShape(50.dp),
            colors = CardDefaults.cardColors(
                containerColor = if (done) {
                    DarkButtons
                } else {
                    BlackStartBackground
                }
            )
        ) {
            if (done) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CommonIcon(
                        size = 40,
                        icon = R.drawable.ic_check,
                        contentDescription = "Check",
                        tint = Color.White
                    )
                }
            }
        }

        CommonSpacer(size = 10)

        // Contenedor de tarea + puntos
        Row(
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(12.dp))
                .background(
                    if (done) {
                        DarkSelectedItems
                    } else {
                        BlackStartBackground
                    }
                )
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CommonText(
                text = tareas,
                fontSize = 16,
                fontWeight = FontWeight.Bold
            )

            // Puntos + ícono
            Row(verticalAlignment = Alignment.CenterVertically) {
                CommonText(
                    text = puntaje.toString(),
                    fontSize = 16,
                    fontWeight = FontWeight.Bold,
                    color = DarkOrange
                )
                CommonSpacer(size = 10)
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
fun CommonUserCard(
    modifier: Modifier = Modifier,
    selected: Boolean,
    tareas: String,
    descripcion: String = "2/10 tareas realizadas",
    puntaje: Int
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(BlackStartBackground)
            .border(
                width = 2.dp,
                color = if (selected) DarkOrange else BlackStartBackground,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
            modifier = Modifier.size(40.dp),
            shape = RoundedCornerShape(50.dp),
            colors = CardDefaults.cardColors(containerColor = DarkButtons)
        ) {}

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 10.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            CommonText(
                text = tareas,
                fontSize = 16,
                fontWeight = FontWeight.Bold
            )
            CommonText(
                text = descripcion,
                fontSize = 16,
                fontWeight = FontWeight.Normal
            )
        }
        // Puntos + ícono
        Row(verticalAlignment = Alignment.CenterVertically) {
            CommonText(
                text = puntaje.toString(),
                fontSize = 16,
                fontWeight = FontWeight.Bold,
                color = DarkOrange
            )
            CommonSpacer(size = 10)
            CommonIcon(
                size = 20,
                icon = R.drawable.ic_coin,
                contentDescription = "Moneda",
                tint = DarkOrange
            )
        }
    }
}


