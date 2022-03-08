package com.onats.characters_ui_components.uitests

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.onats.characters_ui_components.presentation.components.CharacterSummaryCard
import org.junit.Rule
import org.junit.Test

@ExperimentalMaterialApi
class CharacterSummaryCardTest {

    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun testThatCharacterSummaryCardDisplaysCorrectDetails() {
        composeTestRule.setContent {
            CharacterSummaryCard(character = testCharacterSummary) {

            }
        }

        val nameVariable = composeTestRule.onNodeWithText(testCharacterSummary.name)
        val specieVariable = composeTestRule.onNodeWithText(testCharacterSummary.species)
        val episodesVariable = composeTestRule.onNodeWithText("${testCharacterSummary.episodes} episodes")
        nameVariable.assertExists()
        specieVariable.assertExists()
        episodesVariable.assertExists()
    }

    @Test
    fun testThatCharacterCardIsClickable() {
        var clicked = false
        composeTestRule.setContent {
            CharacterSummaryCard(
                character = testCharacterSummary,
                onCharacterClick = { clicked = true }
            )
        }
        val characterCard = composeTestRule.onNodeWithTag("characterCard")
        characterCard.performClick()
        assert(clicked)
    }
}