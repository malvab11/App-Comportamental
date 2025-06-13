package com.example.behaveapp.ui.screens.commons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
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
    lineHeight: TextUnit = TextUnit.Unspecified,
    maxLines: Int = 1
) {
    Text(
        text = text,
        color = color,
        fontWeight = fontWeight,
        fontSize = fontSize.sp,
        lineHeight = lineHeight,
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
    tint: Color,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .size(size.dp)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier
                .size(size.dp),
            painter = painterResource(icon),
            contentDescription = contentDescription,
            tint = tint
        )
    }

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
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.outlinedButtonColors(containerColor = containterColor),
        border = BorderStroke(1.dp, borderColor),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (icon != 0) {
                CommonImage(modifier = Modifier.size(30.dp), imagen = icon, contentDescription = "")
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
fun CommonCircularProgress(modifier: Modifier = Modifier) {
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
    isPassword: Boolean = false,
    value: String = "",
    isShown: Boolean = false,
    isError: Boolean = false,
    errorMessage: String? = null,
    onClick: () -> Unit = {},
    onValueChange: (String) -> Unit = {}
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        onValueChange = { onValueChange(it) },
        label = {
            CommonText(
                modifier = Modifier,
                text = placeholder,
                fontSize = 16
            )
        },
        isError = isError,
        trailingIcon = {
            if (isPassword) {
                IconButton(onClick = { onClick() }) {
                    Icon(
                        if (isShown) painterResource(R.drawable.ic_visibility_off) else painterResource(
                            R.drawable.ic_visibility
                        ),
                        contentDescription = "Icono de Contraseña"
                    )
                }
            }
        },
        visualTransformation = if (!isPassword || isShown) VisualTransformation.None else PasswordVisualTransformation(),
        supportingText = {
            if (isError && errorMessage != null) {
                CommonText(
                    text = errorMessage,
                    color = Color.Red,
                    fontSize = 12
                )
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
                .padding(horizontal = 20.dp, vertical = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CommonText(
                modifier = Modifier.weight(1f),
                text = tareas,
                fontSize = 16,
                fontWeight = FontWeight.Bold,
                maxLines = 4,
                textAlign = TextAlign.Start
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
    descripcion2: String = "",
    puntaje: Int,
    tipo: Int = 1
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
                text = descripcion.takeIf { tipo == 1 } ?: descripcion2,
                fontSize = 16,
                fontWeight = FontWeight.Normal
            )
        }
        if (tipo == 1) {
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
        } else {
            // Puntos + ícono
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                CommonIcon(
                    size = 24,
                    icon = R.drawable.ic_check,
                    contentDescription = "Aceptar",
                    tint = DarkOrange
                )
                CommonSpacer(size = 5)
                CommonIcon(
                    size = 24,
                    icon = R.drawable.ic_times,
                    contentDescription = "Eliminar",
                    tint = DarkOrange
                )
            }
        }
    }
}

@Composable
fun CommonCard(
    modifier: Modifier = Modifier,
    texto: String,
    icono: Int,
    contentDescription: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Contenedor de tarea + puntos
        Row(
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(12.dp))
                .background(DarkUnselectedItems)
                .padding(horizontal = 20.dp, vertical = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CommonText(
                text = texto,
                fontSize = 16,
                fontWeight = FontWeight.Bold
            )
            CommonIcon(
                size = 20,
                icon = icono,
                contentDescription = contentDescription,
                tint = Color.White
            )

        }
    }
}


