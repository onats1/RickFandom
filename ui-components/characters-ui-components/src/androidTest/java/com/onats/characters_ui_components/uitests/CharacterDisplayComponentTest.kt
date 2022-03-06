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
        val loadingProgress = composeTestRule.onNode(hasTestTag("characterDisplayProgress"))

        loadingProgress.assertIsDisplayed()
    }

    @Test
    fun testThatLoadedStateHidesProgressIndicator() {
        composeTestRule.setContent {
            CharactersDisplayComponent(state = charactersLoadedState)
        }
        val loadingProgress = composeTestRule.onNode(hasTestTag("characterDisplayProgress"))

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
        verticalGrid.performScrollToIndex(9).assertExists()
    }
}