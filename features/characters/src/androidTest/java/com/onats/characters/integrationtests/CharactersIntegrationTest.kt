package com.onats.characters.integrationtests

import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.onats.characters.ui.CharacterMainActivity
import com.onats.characters.ui.CharacterViewModelImpl
import com.onats.characters_ui_components.presentation.intents.ExecuteQuery
import com.onats.characters_ui_components.screens.CharactersScreen
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@[RunWith(AndroidJUnit4::class) HiltAndroidTest ExperimentalMaterialApi ExperimentalFoundationApi]
class CharactersIntegrationTest {

    @get:Rule(order = 1)
    var hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    var composeTestRule = createAndroidComposeRule<CharacterMainActivity>()


    @Before
    fun init() {
        hiltTestRule.inject()
    }

    @Test
    fun test_screen_gets_loaded()  {

        composeTestRule.setContent {
            val characterScreenViewModel = composeTestRule.activity.viewModels<CharacterViewModelImpl>().value

            CharactersScreen(characterScreenViewModel = characterScreenViewModel)
        }
        composeTestRule.onNodeWithTag("character_screen_tag").assertIsDisplayed()
        composeTestRule.onNodeWithTag("query_header").assertIsDisplayed()

        //composeTestRule.onNodeWithTag("characters_display").assertIsDisplayed()
    }

    @Test
    fun test_query_behaviour() {
        composeTestRule.setContent {
            val characterScreenViewModel = composeTestRule.activity.viewModels<CharacterViewModelImpl>().value

            CharactersScreen(characterScreenViewModel = characterScreenViewModel)

            characterScreenViewModel.processIntent(ExecuteQuery("test query"))
        }
    }
}