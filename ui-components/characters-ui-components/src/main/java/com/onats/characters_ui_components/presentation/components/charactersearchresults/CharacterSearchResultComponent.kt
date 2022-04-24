package com.onats.characters_ui_components.presentation.components.charactersearchresults

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.onats.characters_ui_components.presentation.components.CharacterSummaryCard
import com.onats.characters_ui_components.presentation.components.charactersearchresults.charactersearchresultstate.CharacterSearchComponentStates
import com.onats.common_ui.components.Center

@[Composable ExperimentalFoundationApi ExperimentalMaterialApi]
fun CharacterSearchResultComponent(
    modifier: Modifier = Modifier,
    state: CharacterSearchComponentStates = CharacterSearchComponentStates.InitialState
) {
    when (state) {
        is CharacterSearchComponentStates.LoadingState -> {
            Center {
                CircularProgressIndicator(
                    modifier = Modifier.testTag("characterSearchProgress")
                )
            }
        }
        is CharacterSearchComponentStates.CharactersSearched -> {
            val characters = state.data.queryResults
            LazyVerticalGrid(
                cells = GridCells.Fixed(2),
                contentPadding = PaddingValues(
                    start = 16.dp,
                    end = 8.dp,
                    top = 16.dp,
                    bottom = 56.dp
                ),
                modifier = Modifier.testTag("charactersSearchGridDisplay")
            ) {
                items(characters.size) {
                    CharacterSummaryCard(character = characters[it]) {

                    }
                }
            }
        }
    }
}