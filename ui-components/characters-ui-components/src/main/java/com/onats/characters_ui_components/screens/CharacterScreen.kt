package com.onats.characters_ui_components.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import com.onats.characters_ui_components.presentation.CharacterScreenStates
import com.onats.characters_ui_components.presentation.CharacterViewModel
import com.onats.characters_ui_components.presentation.components.characterdisplaycomponent.CharactersDisplayComponent
import com.onats.characters_ui_components.presentation.components.characterqueryfieldcomponent.CharacterQueryHeader
import com.onats.characters_ui_components.presentation.intents.QueryCharacters

@[Composable ExperimentalMaterialApi ExperimentalFoundationApi]
fun CharactersScreen(
    characterScreenViewModel: CharacterViewModel
) {
    val charactersState = characterScreenViewModel.screenState.collectAsState()

    Scaffold(
        backgroundColor = Color.White,
        topBar = {
            CharacterQueryHeader(
                queryFieldComponentStates = charactersState.value.characterScreenComponents.characterQueryFieldData,
                onQueryValueChanged = { characterScreenViewModel.processIntent(QueryCharacters(it)) }
            )
        }
    ) {
        when (val screenStateValue = charactersState.value) {
            is CharacterScreenStates.CharacterDisplayComponentState -> {
                CharactersDisplayComponent(state = screenStateValue.characterScreenComponents.characterData)
            }
        }
    }
}

