package com.onats.characters.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.onats.characters_ui_components.screens.CharactersScreen

@[Composable ExperimentalMaterialApi ExperimentalFoundationApi]
fun CharactersBaseDisplay() {
    CharactersScreen(
        characterScreenViewModel = hiltViewModel<CharacterViewModelImpl>()
    )
}