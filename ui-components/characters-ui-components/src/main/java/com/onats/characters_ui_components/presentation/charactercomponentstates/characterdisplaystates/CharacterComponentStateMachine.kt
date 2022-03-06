package com.onats.characters_ui_components.presentation.charactercomponentstates.characterdisplaystates

import com.onats.characters_ui_components.presentation.charactercomponentstates.*
import com.onats.common_ui.presentation.MviResults
import com.onats.core_character.models.Character

object CharacterComponentStateMachine {

    fun transform(
        results: CharacterComponentResults,
        screenStates: CharacterScreenStates,
    ): CharacterScreenStates.CharacterDisplayComponentState {

        val characterComponentState = screenStates.characterScreenComponents.characterData

        return when (results) {
            is CharacterComponentResults.Loading -> {
                val characterLoadingState = CharacterDisplayComponentStates.LoadingState(characterComponentState.reduceToLoadingState())
                val updatedState = screenStates.reduceToCharacterDisplayState(characterLoadingState)
                CharacterScreenStates.CharacterDisplayComponentState(updatedState)
            }
            is CharacterComponentResults.CharactersLoaded -> {
                val updatedCharacterDisplayData = characterComponentState.reduceToCharactersLoadedState(results.characters.map { it.summary })
                val updatedCharacterDisplayComponent = screenStates.reduceToCharacterDisplayState(
                    CharacterDisplayComponentStates.CharactersLoaded(updatedCharacterDisplayData)
                )
                CharacterScreenStates.CharacterDisplayComponentState(
                    updatedCharacterDisplayComponent
                )
            }
        }
    }
}

sealed class CharacterComponentResults: MviResults {
    object Loading: CharacterComponentResults()
    data class CharactersLoaded(val characters: List<Character>): CharacterComponentResults()
}



