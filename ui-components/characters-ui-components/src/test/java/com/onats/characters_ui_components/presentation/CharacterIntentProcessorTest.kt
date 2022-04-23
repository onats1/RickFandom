package com.onats.characters_ui_components.presentation

import com.google.common.truth.Truth.assertThat
import com.onats.characters_ui_components.fakes.GetAllCharactersUseCaseMock
import com.onats.characters_ui_components.fakes.testCharacters
import com.onats.characters_ui_components.presentation.components.characterdisplaycomponent.characterdisplaystates.CharacterDisplayComponentStates
import com.onats.characters_ui_components.presentation.components.characterdisplaycomponent.characterdisplaystates.CharacterDisplayData
import com.onats.characters_ui_components.presentation.intents.CharacterIntentProcessor
import com.onats.characters_ui_components.presentation.intents.LoadCharacters
import com.onats.characters_ui_components.presentation.intents.QueryInProgress
import com.onats.core_character.usecases.GetAllCharactersUseCase
import kotlinx.coroutines.*
import org.junit.Test


class CharacterIntentProcessorTest {

    private val mockCharacterUseCase: GetAllCharactersUseCase = GetAllCharactersUseCaseMock()

    private val characterIntentProcessor = CharacterIntentProcessor(mockCharacterUseCase)

    @Test
    fun `test loaded intent returns characters loaded`() = runBlocking {

        val defaultScreenState = CharacterScreenStates.InitialState

        characterIntentProcessor.processIntent(LoadCharacters, defaultScreenState) { processedResult ->
            assertThat(processedResult).isInstanceOf(CharacterScreenStates.CharacterDisplayComponentState::class.java)
        }
    }

    @Test
    fun `test that query field returns correct result`() = runBlocking {
        val charactersLoadedScreenState = CharacterScreenStates.CharacterDisplayComponentState(
            characterDisplayComponents = CharacterScreenComponents(
                characterData = CharacterDisplayComponentStates.CharactersLoaded(
                    characterData = CharacterDisplayData(
                        characters = testCharacters
                    )
                )
            )
        )

        characterIntentProcessor.processIntent(QueryInProgress("test"), charactersLoadedScreenState) { resultState ->
            assertThat(resultState).isInstanceOf(CharacterScreenStates.CharacterQueryFieldComponentState::class.java)
        }
    }

}