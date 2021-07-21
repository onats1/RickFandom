package com.onats.characters.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.onats.characters.R
import com.onats.characters.ui.characterstates.CharacterDisplayStates
import com.onats.characters_ui_components.CharacterSummaryCard
import com.onats.common_ui.components.AppBarInfo
import com.onats.common_ui.components.Center
import com.onats.common_ui.theme.RickFandomTheme
import com.onats.core_character.models.CharacterSummary
import timber.log.Timber

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun CharactersScreen() {

    var text by rememberSaveable { mutableStateOf("") } // Will be updated accordingly
    val viewModel = hiltViewModel<CharacterViewModel>()

    val charactersState = viewModel.characterState.collectAsState()


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
        if (charactersState.value is CharacterDisplayStates.LoadingState) {
            Center {
                CircularProgressIndicator()
            }
        } else if (charactersState.value is CharacterDisplayStates.CharactersLoaded) {
            val characters =
                (charactersState.value as CharacterDisplayStates.CharactersLoaded).characters
            LazyVerticalGrid(
                cells = GridCells.Fixed(2),
                contentPadding = PaddingValues(
                    start = 16.dp,
                    end = 8.dp,
                    top = 16.dp,
                    bottom = 56.dp
                ),
            ) {
                items(characters.size) {
                    CharacterSummaryCard(character = characters[it]) {

                    }
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Preview()
@Composable
fun PreviewCharactersLayout() {
    RickFandomTheme {
        CharactersScreen()
    }
}