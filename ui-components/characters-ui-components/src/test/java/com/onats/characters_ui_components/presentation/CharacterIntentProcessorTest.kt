package com.onats.characters_ui_components.presentation

import com.google.common.truth.Truth.assertThat
import com.onats.characters_ui_components.fakes.GetAllCharactersUseCaseMock
import com.onats.characters_ui_components.presentation.characterstates.CharacterDisplayScreenStates
import com.onats.characters_ui_components.presentation.intents.CharacterIntentProcessor
import com.onats.characters_ui_components.presentation.intents.LoadCharacters
import com.onats.core_character.usecases.GetAllCharactersUseCase
import kotlinx.coroutines.*
import org.junit.Test
import org.mockito.Mock


class CharacterIntentProcessorTest {

    private val mockCharacterUseCase: GetAllCharactersUseCase = GetAllCharactersUseCaseMock()

    private val characterIntentProcessor = CharacterIntentProcessor(mockCharacterUseCase)

    @Test
    fun `test loaded intent returns characters loaded`() = runBlocking {

        val defaultScreenState = CharacterDisplayScreenStates.InitialState

        characterIntentProcessor.processIntent(LoadCharacters, defaultScreenState) { processedResult ->
            assertThat(processedResult).isInstanceOf(CharacterDisplayScreenStates.CharacterDisplayComponentState::class.java)
        }
    }

}