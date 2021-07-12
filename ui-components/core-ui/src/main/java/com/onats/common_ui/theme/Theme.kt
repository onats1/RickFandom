package com.onats.common_ui.theme

import android.view.Window
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = RickBlue,
    primaryVariant = RickBlueVariant,
    onPrimary = Color.White,
    secondary = MortyYellow,
    secondaryVariant = MortyYellowVariant,
    onSecondary = Color.White,
    onError = Red200
)

private val LightColorPalette = lightColors(
    primary = RickBlue,
    primaryVariant = RickBlueVariant,
    onPrimary = Color.Black,
    secondary = MortyYellow,
    secondaryVariant = MortyYellowVariant,
    onSecondary = Color.Black,
    onError = Red800

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
fun RickFandomTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    systemUiController: SystemUiController = rememberSystemUiController(),
    content: @Composable() () -> Unit,
) {
    SideEffect {
        if (darkTheme) {
            systemUiController.setStatusBarColor(
                color = RickBlueVariant,
                darkIcons = true
            )
        } else {
            systemUiController.setStatusBarColor(color = RickBlueVariant)
        }
    }
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