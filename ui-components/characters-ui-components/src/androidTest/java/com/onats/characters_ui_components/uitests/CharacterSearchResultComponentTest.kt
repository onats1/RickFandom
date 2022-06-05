package com.onats.characters_ui_components.uitests

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.onats.characters_ui_components.components.charactersearchresults.CharacterSearchResultComponent
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
        composeTestRule.onNodeWithTag("character_search_progress").assertIsDisplayed()
    }

    @Test
    fun test_that_success_state_shows_grid() {
        composeTestRule.setContent {
            CharacterSearchResultComponent(
                state = charactersSearchLoadedState
            )
        }
        composeTestRule.onNodeWithTag("character_search_progress").assertDoesNotExist()
        val verticalGrid = composeTestRule.onNodeWithTag("characters_search_grid_display").assertIsDisplayed()
        verticalGrid.assertExists()
        verticalGrid.assertIsDisplayed()
        val lastVerticalGridItem = charactersSearchLoadedState.searchData.queryResults.size / 2
        verticalGrid.performScrollToIndex(lastVerticalGridItem - 1).assertExists()
    }

    @Test
    fun test_that_error_state_shows_error_layout() {
        composeTestRule.setContent {
            CharacterSearchResultComponent(
                state = charactersSearchErrorState
            )
        }
        val errorLayout = composeTestRule.onNodeWithTag("search_results_error_layout")
        errorLayout.assertExists()
        errorLayout.assertIsDisplayed()
    }

    @Test
    fun test_that_empty_result_state_shows_retry_button() {
        composeTestRule.setContent {
            CharacterSearchResultComponent(
                state = characterNotFoundErrorState
            )
        }
        val retryLayout = composeTestRule.onNodeWithTag("query_not_found_text")
        retryLayout.assertExists()
        retryLayout.assertIsDisplayed()
    }

    @Test
    fun test_that_retry_button_works() {
        var retried = false
        composeTestRule.setContent {
            CharacterSearchResultComponent(
                state = charactersSearchErrorState
            ) {
                retried = true
            }
        }
        val button = composeTestRule.onNodeWithTag("search_results_retry_button")
        button.performClick()
        assert(retried)
    }
}