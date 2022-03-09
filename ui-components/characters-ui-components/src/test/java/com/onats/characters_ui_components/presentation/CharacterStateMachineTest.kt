package com.onats.characters_ui_components.presentation

import com.google.common.truth.Truth.assertThat
import com.onats.characters_ui_components.presentation.components.characterdisplaycomponent.characterdisplaystates.CharacterDisplayComponentStates
import com.onats.characters_ui_components.presentation.components.characterdisplaycomponent.characterdisplaystates.CharacterComponentResults
import com.onats.characters_ui_components.presentation.components.characterdisplaycomponent.characterdisplaystates.CharacterComponentStateMachine
import org.junit.Test

class CharacterStateMachineTest {

    @Test
    fun `test that loading result transforms to component loading state`() {
        val characterDisplayComponentResult = CharacterComponentStateMachine.transform(
            CharacterComponentResults.Loading, CharacterScreenStates.InitialState)

        assertThat(characterDisplayComponentResult.characterScreenComponents.characterData).isInstanceOf(
            CharacterDisplayComponentStates.LoadingState::class.java)
    }

    @Test
    fun `test that characters loaded state transforms to component loaded state`() {
        val componentResult = CharacterComponentStateMachine.transform(
            CharacterComponentResults.CharactersLoaded(listOf()),
            CharacterScreenStates.InitialState
        )

        assertThat(componentResult.characterScreenComponents.characterData).isInstanceOf(
            CharacterDisplayComponentStates.CharactersLoaded::class.java)
    }
}