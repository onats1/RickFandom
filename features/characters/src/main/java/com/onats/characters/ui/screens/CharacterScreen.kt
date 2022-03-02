package com.onats.characters.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
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
import com.onats.characters.ui.CharacterViewModel
import com.onats.characters_ui_components.CharacterSummaryCard
import com.onats.characters_ui_components.presentation.characterstates.CharacterDisplayComponentStates
import com.onats.characters_ui_components.presentation.characterstates.CharacterDisplayScreenStates
import com.onats.common_ui.components.AppBarInfo
import com.onats.common_ui.components.Center
import com.onats.common_ui.theme.RickFandomTheme
import timber.log.Timber

@[Composable ExperimentalMaterialApi ExperimentalFoundationApi]
fun CharactersScreen() {

    var text by rememberSaveable { mutableStateOf("") } // Will be updated accordingly
    val viewModel = hiltViewModel<CharacterViewModel>()

    val charactersState = viewModel.screenState.collectAsState()


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
                if (screenStateValue.characterScreenData.characterData is CharacterDisplayComponentStates.LoadingState) {
                    Center {
                        CircularProgressIndicator()
                    }
                } else {
                    val characters = screenStateValue.characterScreenData.characterData.data.characters
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