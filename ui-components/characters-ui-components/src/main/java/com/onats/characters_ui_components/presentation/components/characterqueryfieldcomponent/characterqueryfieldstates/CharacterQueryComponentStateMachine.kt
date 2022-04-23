package com.onats.characters_ui_components.presentation.components.characterqueryfieldcomponent.characterqueryfieldstates

import com.onats.characters_ui_components.presentation.CharacterScreenStates
import com.onats.characters_ui_components.presentation.components.characterdisplaycomponent.characterdisplaystates.CharacterDisplayComponentStates
import com.onats.characters_ui_components.presentation.reduceToCharacterDisplayState
import com.onats.characters_ui_components.presentation.reduceToCharacterQueryDisplayState
import com.onats.common_ui.presentation.MviResults
import timber.log.Timber

object CharacterQueryComponentStateMachine {

    fun transform(
        results: CharacterQueryFieldResults,
        screenStates: CharacterScreenStates,
    ): CharacterScreenStates.CharacterQueryFieldComponentState {

        val characterQueryFieldComponentState = screenStates.characterScreenComponents.characterQueryFieldData

        return when (results) {
            is CharacterQueryFieldResults.SearchQuery-> {
                val characterQueryInProgressState = CharacterQueryFieldComponentStates.QueryInProgress(characterQueryFieldComponentState.reduceToQueryInProgressState(results.query))
                val updatedScreenState = screenStates.reduceToCharacterQueryDisplayState(characterQueryInProgressState)
                CharacterScreenStates.CharacterQueryFieldComponentState(updatedScreenState)
            }
            is CharacterQueryFieldResults.EmptyQuery -> {
                val emptyQueryState = CharacterQueryFieldComponentStates.InitialState
                val updatedScreenState = screenStates.reduceToCharacterQueryDisplayState(emptyQueryState)
                CharacterScreenStates.CharacterQueryFieldComponentState(updatedScreenState)
            }
        }
    }
}

sealed class CharacterQueryFieldResults: MviResults {
    data class SearchQuery(val query: String): CharacterQueryFieldResults()
    object EmptyQuery: CharacterQueryFieldResults()
}