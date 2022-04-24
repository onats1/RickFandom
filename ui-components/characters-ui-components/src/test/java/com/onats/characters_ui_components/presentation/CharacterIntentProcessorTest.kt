package com.onats.characters_ui_components.presentation

import com.google.common.truth.Truth.assertThat
import com.onats.characters_ui_components.fakes.GetAllCharactersUseCaseMock
import com.onats.characters_ui_components.fakes.SearchCharactersUseCaseMock
import com.onats.characters_ui_components.fakes.testCharactersSummary
import com.onats.characters_ui_components.presentation.components.characterdisplaycomponent.characterdisplaystates.CharacterDisplayComponentStates
import com.onats.characters_ui_components.presentation.components.characterdisplaycomponent.characterdisplaystates.CharacterDisplayData
import com.onats.characters_ui_components.presentation.components.charactersearchresults.charactersearchresultstate.CharacterSearchComponentStates
import com.onats.characters_ui_components.presentation.components.charactersearchresults.charactersearchresultstate.CharacterSearchErrorTypes
import com.onats.characters_ui_components.presentation.intents.CharacterIntentProcessor
import com.onats.characters_ui_components.presentation.intents.ExecuteQuery
import com.onats.characters_ui_components.presentation.intents.LoadCharacters
import com.onats.characters_ui_components.presentation.intents.QueryInProgress
import com.onats.common.DomainResult
import com.onats.core_character.models.Character
import com.onats.core_character.usecases.GetAllCharactersUseCase
import com.onats.core_character.usecases.SearchQueryRequest
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flow
import org.junit.Test
import org.mockito.Mockito.`when`


class CharacterIntentProcessorTest {

    private val mockCharacterUseCase: GetAllCharactersUseCase = GetAllCharactersUseCaseMock()

    private val mockSearchCharacterUseCase = SearchCharactersUseCaseMock()

    private val characterIntentProcessor = CharacterIntentProcessor(mockCharacterUseCase, mockSearchCharacterUseCase)

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
                        characters = testCharactersSummary
                    )
                )
            )
        )

        characterIntentProcessor.processIntent(QueryInProgress("test"), charactersLoadedScreenState) { resultState ->
            assertThat(resultState).isInstanceOf(CharacterScreenStates.CharacterQueryFieldComponentState::class.java)
        }
    }

    @Test
    fun `test that character search returns correct result`() = runBlocking {
        val charactersLoadedScreenState = CharacterScreenStates.CharacterDisplayComponentState(
            characterDisplayComponents = CharacterScreenComponents(
                characterData = CharacterDisplayComponentStates.CharactersLoaded(
                    characterData = CharacterDisplayData(
                        characters = testCharactersSummary
                    )
                )
            )
        )

        characterIntentProcessor.processIntent(ExecuteQuery("error"), charactersLoadedScreenState) { resultState ->
            assertThat(resultState).isInstanceOf(CharacterScreenStates.CharacterSearchResultsState::class.java)
            val errorState = resultState.characterScreenComponents.characterSearchData.data.errorType
            assertThat(errorState).isInstanceOf(CharacterSearchErrorTypes.NETWORK_ERROR::class.java)
            println("$errorState")
        }
    }

    @Test
    fun `test that empty character search returns empty error state`() = runBlocking {
        val charactersLoadedScreenState = CharacterScreenStates.CharacterDisplayComponentState(
            characterDisplayComponents = CharacterScreenComponents(
                characterData = CharacterDisplayComponentStates.CharactersLoaded(
                    characterData = CharacterDisplayData(
                        characters = testCharactersSummary
                    )
                )
            )
        )

        characterIntentProcessor.processIntent(ExecuteQuery("test"), charactersLoadedScreenState) { resultState ->
            assertThat(resultState).isInstanceOf(CharacterScreenStates.CharacterSearchResultsState::class.java)
        }
    }

}