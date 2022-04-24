package com.onats.characters_ui_components.presentation.components.characterdisplaycomponent

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.onats.characters_ui_components.R
import com.onats.characters_ui_components.presentation.components.CharacterSummaryCard
import com.onats.characters_ui_components.presentation.components.characterdisplaycomponent.characterdisplaystates.CharacterDisplayComponentStates
import com.onats.common_ui.components.Center

@[Composable ExperimentalFoundationApi ExperimentalMaterialApi]
fun CharactersDisplayComponent(
    modifier: Modifier = Modifier,
    state: CharacterDisplayComponentStates,
    reloadAction: () -> Unit = {}
) {
    when (state) {
        is CharacterDisplayComponentStates.LoadingState -> {
            Center {
                CircularProgressIndicator(
                    modifier = Modifier.testTag("character_loading_progress")
                )
            }
        }
        is CharacterDisplayComponentStates.ErrorState -> {
            val errorMessage = state.characterData.errorData.errorMessage
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize().testTag("search_error_layout")
            ) {
                Text(
                    text = stringResource(id = errorMessage),
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center
                )
                Button(
                    onClick = { reloadAction() },
                    modifier = Modifier
                        .width(100.dp)
                        .height(50.dp)
                        .padding(top = 20.dp)
                        .testTag("search_display_retry_button")
                        .background(color = MaterialTheme.colors.primary, shape = RoundedCornerShape(50.dp)),
                ) {
                    Text(text = stringResource(id = R.string.retry))
                }
            }
        }
        else -> {
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
}