package com.example.todoesdraslista.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


private val DarkColorPalette = darkColors(
    primary = AlmostBlack01,
    onPrimary = Color.White,
    primaryVariant = AlmostBlack02,
    secondary = AlmostBlack03,
    onSecondary = Color.White,
    onBackground=Color(0xFFC4ABFF)
)

private val LightColorPalette = lightColors(
    primary = AlmostWhite01,
    onPrimary = Color.Black,
    primaryVariant = AlmostWhite02,
    secondary = AlmostWhite03,
    onSecondary = Color.Black ,
    onBackground=Color(0xFF6901FF)


    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun TodoEsdrasListaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}