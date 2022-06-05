package com.onats.characters_ui_components.components.charactersearchresults

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
import com.onats.characters_ui_components.components.CharacterSummaryCard
import com.onats.characters_ui_components.components.charactersearchresults.charactersearchresultstate.CharacterSearchComponentStates
import com.onats.characters_ui_components.components.charactersearchresults.charactersearchresultstate.CharacterSearchErrorTypes
import com.onats.common_ui.components.Center

@[Composable ExperimentalFoundationApi ExperimentalMaterialApi]
fun CharacterSearchResultComponent(
    modifier: Modifier = Modifier,
    state: CharacterSearchComponentStates = CharacterSearchComponentStates.InitialState,
    reloadAction: () -> Unit = {}
) {
    when (state) {
        is CharacterSearchComponentStates.LoadingState -> {
            Center {
                CircularProgressIndicator(
                    modifier = Modifier.testTag("character_search_progress")
                )
            }
        }
        is CharacterSearchComponentStates.ErrorState -> {
            when (val errorType = state.searchData.errorType) {
                CharacterSearchErrorTypes.NO_MATCHING_RESULTS -> {
                    Center {
                        Text(
                            text = stringResource(R.string.query_not_found),
                            fontSize = 20.sp,
                            modifier = Modifier.testTag("query_not_found_text"),
                            textAlign = TextAlign.Center
                        )
                    }
                }
                CharacterSearchErrorTypes.NETWORK_ERROR -> {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxSize().testTag("search_results_error_layout")
                    ) {
                        Text(
                            text = stringResource(id = errorType.errorMessage),
                            fontSize = 24.sp,
                            textAlign = TextAlign.Center
                        )
                        Button(
                            onClick = { reloadAction() },
                            modifier = Modifier
                                .width(100.dp)
                                .height(50.dp)
                                .padding(top = 20.dp)
                                .testTag("search_results_retry_button")
                                .background(color = MaterialTheme.colors.primary, shape = RoundedCornerShape(50.dp)),
                        ) {
                            Text(text = stringResource(id = R.string.retry))
                        }
                    }
                }
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
                modifier = Modifier.testTag("characters_search_grid_display")
            ) {
                items(characters.size) {
                    CharacterSummaryCard(character = characters[it]) {

                    }
                }
            }
        }
        else -> {}
    }
}