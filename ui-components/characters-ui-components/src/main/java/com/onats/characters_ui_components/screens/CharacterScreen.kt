package com.onats.characters_ui_components.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.onats.characters_ui_components.R
import com.onats.characters_ui_components.presentation.CharacterViewModel
import com.onats.characters_ui_components.presentation.characterstates.CharacterDisplayScreenStates
import com.onats.characters_ui_components.presentation.components.CharactersDisplayComponent
import com.onats.common_ui.components.AppBarInfo
import timber.log.Timber

@[Composable ExperimentalMaterialApi ExperimentalFoundationApi]
fun CharactersScreen(
    characterScreenViewModel: CharacterViewModel
) {
    var text by rememberSaveable { mutableStateOf("") } // Will be updated accordingly
    val charactersState = characterScreenViewModel.screenState.collectAsState()

    Scaffold(
        backgroundColor = Color.White,
        topBar = {
            AppBarInfo(
                title = stringResource(id = R.string.character),
                searchValue = text,
            ) { searchValue ->
                text = searchValue
                Timber.e(searchValue)
            }
        }
    ) {
        when (val screenStateValue = charactersState.value) {
            is CharacterDisplayScreenStates.CharacterDisplayComponentState -> {
                CharactersDisplayComponent(state = screenStateValue.characterScreenData.characterData)
            }
        }
    }
}

