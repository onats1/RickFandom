package com.onats.characters_ui_components.uitests

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.onats.characters_ui_components.presentation.CharacterViewModel
import com.onats.characters_ui_components.presentation.components.characterqueryfieldcomponent.CharacterQueryHeader
import com.onats.characters_ui_components.screens.CharactersScreen
import com.onats.common_ui.presentation.MVIIntent
import org.junit.Rule
import org.junit.Test

@[ExperimentalFoundationApi ExperimentalMaterialApi ExperimentalComposeUiApi]
class CharacterQueryHeaderTest {
    
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun test_Query_Header_is_displayed() {
        composeTestRule.setContent { 
            CharactersScreen(characterScreenViewModel = object : CharacterViewModel() {
                override fun processIntent(intent: MVIIntent) {

                }
            })
        }

        val header = composeTestRule.onNodeWithTag("query_header")
        header.assertExists()
        header.assertIsDisplayed()
    }

    @Test
    fun test_query_header_parses_filters_correct_strings() {
        composeTestRule.setContent {
            CharacterQueryHeader(
                onQueryValueChanged = {},
                executeQuery = {}
            )
        }
    }
}