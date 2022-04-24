package com.onats.characters_ui_components.presentation.components.charactersearchresults.charactersearchresultstate

import com.onats.characters_ui_components.presentation.CharacterScreenComponents
import com.onats.characters_ui_components.presentation.CharacterScreenStates
import com.onats.characters_ui_components.presentation.reduceToCharactersQueriedState
import com.onats.common_ui.presentation.MviResults
import com.onats.core_character.models.Character

object CharacterSearchComponentStateMachine {

    fun transform(
        results: CharacterSearchComponentResult,
        screenStates: CharacterScreenStates,
    ): CharacterScreenStates.CharacterSearchResultsState {
        val characterSearchComponentState =
            screenStates.characterScreenComponents.characterSearchData

        when (results) {
            CharacterSearchComponentResult.Loading -> {
                val updatedState =
                    CharacterSearchComponentStates.LoadingState(characterSearchComponentState.reduceToLoadingState())
                val updatedScreenState = screenStates.reduceToCharactersQueriedState(updatedState)
                return CharacterScreenStates.CharacterSearchResultsState(updatedScreenState)
            }

            is CharacterSearchComponentResult.SuccessResult -> {
                val updatedState = CharacterSearchComponentStates.CharactersSearched(
                    characterSearchComponentState.reduceToCharactersLoadedState(results.queryMatches.map { it.summary })
                )
                val updatedScreenState = screenStates.reduceToCharactersQueriedState(updatedState)
                return CharacterScreenStates.CharacterSearchResultsState(updatedScreenState)
            }
        }
    }
}

sealed class CharacterSearchComponentResult : MviResults {
    data class SuccessResult(
        val queryMatches: List<Character>
    ) : CharacterSearchComponentResult()

    object Loading : CharacterSearchComponentResult()
}