package com.onats.characters_ui_components.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import com.onats.characters_ui_components.presentation.CharacterScreenStates
import com.onats.characters_ui_components.presentation.CharacterViewModel
import com.onats.characters_ui_components.presentation.components.characterdisplaycomponent.CharactersDisplayComponent
import com.onats.characters_ui_components.presentation.components.characterqueryfieldcomponent.CharacterQueryHeader
import com.onats.characters_ui_components.presentation.components.characterqueryfieldcomponent.characterqueryfieldstates.CharacterQueryFieldComponentStates
import com.onats.characters_ui_components.presentation.components.charactersearchresults.CharacterSearchResultComponent
import com.onats.characters_ui_components.presentation.intents.ExecuteQuery
import com.onats.characters_ui_components.presentation.intents.QueryInProgress

@[Composable ExperimentalMaterialApi ExperimentalFoundationApi]
fun CharactersScreen(
    characterScreenViewModel: CharacterViewModel
) {
    val charactersState = characterScreenViewModel.screenState.collectAsState()

    Scaffold(
        modifier = Modifier.testTag("character_screen_tag"),
        backgroundColor = Color.White,
        topBar = {
            CharacterQueryHeader(
                queryFieldComponentStates = charactersState.value.characterScreenComponents.characterQueryFieldData,
                onQueryValueChanged = { characterScreenViewModel.processIntent(QueryInProgress(query = it)) },
                executeQuery = {
                    characterScreenViewModel.processIntent(ExecuteQuery(it))
                }
            )
        }
    ) {
        when (val screenStateValue = charactersState.value) {
            is CharacterScreenStates.CharacterDisplayComponentState -> {
                CharactersDisplayComponent(
                    modifier = Modifier.testTag("characters_display").testTag("characters_display"),
                    state = screenStateValue.characterScreenComponents.characterData
                )
            }
            is CharacterScreenStates.CharacterQueryFieldComponentState -> {
                when (screenStateValue.characterScreenComponents.characterQueryFieldData) {
                    is CharacterQueryFieldComponentStates.InitialState -> {
                        CharactersDisplayComponent(state = screenStateValue.characterScreenComponents.characterData)
                    }
                    is CharacterQueryFieldComponentStates.QueryInProgress -> {
                        CharacterSearchResultComponent(state = screenStateValue.characterScreenComponents.characterSearchData)
                    }

                }
            }
        }
    }
}

