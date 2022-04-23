package com.onats.characters_ui_components.presentation.components.characterdisplaycomponent

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.onats.characters_ui_components.presentation.components.CharacterSummaryCard
import com.onats.characters_ui_components.presentation.components.characterdisplaycomponent.characterdisplaystates.CharacterDisplayComponentStates
import com.onats.common_ui.components.Center

@[Composable ExperimentalFoundationApi ExperimentalMaterialApi]
fun CharactersDisplayComponent(
    modifier: Modifier = Modifier,
    state: CharacterDisplayComponentStates
) {
    if (state is CharacterDisplayComponentStates.LoadingState) {
        Center {
            CircularProgressIndicator(
                modifier = Modifier.testTag("characterDisplayProgress")
            )
        }
    } else {
        val characters = state.data.characters
        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            contentPadding = PaddingValues(
                start = 16.dp,
                end = 8.dp,
                top = 16.dp,
                bottom = 56.dp
            ),
            modifier = Modifier.testTag("charactersGridDisplay")
        ) {
            items(characters.size) {
                CharacterSummaryCard(character = characters[it]) {

                }
            }
        }
    }
}