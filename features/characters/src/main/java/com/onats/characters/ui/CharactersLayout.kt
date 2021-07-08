package com.onats.characters.ui

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CharactersScreen() {
    Scaffold(
        backgroundColor = Color.Black
    ) {

    }
}

@Preview()
@Composable
fun PreviewCharactersLayout() {
    CharactersScreen()
}