package com.onats.characters_ui_components.uitests

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performScrollToIndex
import com.onats.characters_ui_components.presentation.components.charactersearchresults.CharacterSearchResultComponent
import org.junit.Rule
import org.junit.Test

@[ExperimentalFoundationApi ExperimentalMaterialApi ExperimentalTestApi]
class CharacterSearchResultComponentTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun test_that_loading_state_shows_progress_bar() {
        composeTestRule.setContent {
            CharacterSearchResultComponent(
                state = characterSearchLoadingState
            )
        }
        composeTestRule.onNodeWithTag("characterSearchProgress").assertIsDisplayed()
    }

    @Test
    fun test_that_success_state_shows_grid() {
        composeTestRule.setContent {
            CharacterSearchResultComponent(
                state = charactersSearchLoadedState
            )
        }
        composeTestRule.onNodeWithTag("characterSearchProgress").assertDoesNotExist()
        val verticalGrid = composeTestRule.onNodeWithTag("charactersSearchGridDisplay").assertIsDisplayed()
        verticalGrid.assertExists()
        verticalGrid.assertIsDisplayed()
        val lastVerticalGridItem = charactersSearchLoadedState.searchData.queryResults.size / 2
        verticalGrid.performScrollToIndex(lastVerticalGridItem - 1).assertExists()
    }
}