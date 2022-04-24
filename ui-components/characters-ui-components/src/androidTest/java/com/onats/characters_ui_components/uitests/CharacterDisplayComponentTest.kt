package com.onats.characters_ui_components.uitests

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.onats.characters_ui_components.presentation.components.characterdisplaycomponent.CharactersDisplayComponent
import org.junit.Rule
import org.junit.Test

@[ExperimentalFoundationApi ExperimentalMaterialApi ExperimentalStdlibApi ExperimentalTestApi]
class CharacterDisplayComponentTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testThatLoadingStateShowsProgressIndicator() {
        composeTestRule.setContent {
            CharactersDisplayComponent(state = loadingTestState)
        }
        val loadingProgress = composeTestRule.onNode(hasTestTag("character_loading_progress"))

        loadingProgress.assertIsDisplayed()
    }

    @Test
    fun testThatLoadedStateHidesProgressIndicator() {
        composeTestRule.setContent {
            CharactersDisplayComponent(state = charactersLoadedState)
        }
        val loadingProgress = composeTestRule.onNode(hasTestTag("character_loading_progress"))

        loadingProgress.assertDoesNotExist()
    }

    @Test
    fun testThatLoadedStateShowsVerticalGridLayout() {
        composeTestRule.setContent {
            CharactersDisplayComponent(state = charactersLoadedState)
        }
        val verticalGrid = composeTestRule.onNodeWithTag("charactersGridDisplay")
        verticalGrid.assertExists()
        verticalGrid.assertIsDisplayed()
        val lastVerticalGridItem = charactersLoadedState.characterData.characters.size / 2
        verticalGrid.performScrollToIndex(lastVerticalGridItem - 1).assertExists()
    }

    @Test
    fun testThatErrorStateShowsErrorLayout() {
        composeTestRule.setContent {
            CharactersDisplayComponent(state = charactersErrorState)
        }
        val errorLayout = composeTestRule.onNodeWithTag("search_error_layout")
        errorLayout.assertExists()
        errorLayout.assertIsDisplayed()
    }

    @Test
    fun testThatComponentCanRetryOnErrorState() {
        var retried = false
        composeTestRule.setContent {
            CharactersDisplayComponent(state = charactersErrorState) {
                retried = true
            }
        }
        val button = composeTestRule.onNodeWithTag("search_display_retry_button")
        button.performClick()

        assert(retried)
    }
}