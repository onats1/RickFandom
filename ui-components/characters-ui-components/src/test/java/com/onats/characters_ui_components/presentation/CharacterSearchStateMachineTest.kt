package com.onats.characters_ui_components.presentation

import com.google.common.truth.Truth.assertThat
import com.onats.characters_ui_components.fakes.testCharacters
import com.onats.characters_ui_components.presentation.components.charactersearchresults.charactersearchresultstate.CharacterSearchComponentResult
import com.onats.characters_ui_components.presentation.components.charactersearchresults.charactersearchresultstate.CharacterSearchComponentStateMachine
import com.onats.characters_ui_components.presentation.components.charactersearchresults.charactersearchresultstate.CharacterSearchComponentStates
import kotlinx.coroutines.runBlocking
import org.junit.Test

class CharacterSearchStateMachineTest {

    @Test
    fun `assert that loading result transforms to loading state`() = runBlocking {
        val transform = CharacterSearchComponentStateMachine.transform(
            CharacterSearchComponentResult.Loading,
            CharacterScreenStates.InitialState
        )
        assertThat(transform.characterScreenComponents.characterSearchData).isInstanceOf(
            CharacterSearchComponentStates.LoadingState::class.java
        )
    }

    @Test
    fun `assert that success result transforms to success state`() = runBlocking {
        val transform = CharacterSearchComponentStateMachine.transform(
            CharacterSearchComponentResult.SuccessResult(testCharacters),
            CharacterScreenStates.InitialState
        )
        assertThat(transform.characterScreenComponents.characterSearchData).isInstanceOf(
            CharacterSearchComponentStates.CharactersSearched::class.java
        )
    }
}