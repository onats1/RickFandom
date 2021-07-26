package com.onats.characters.presentation

import com.onats.characters.presentation.factories.CharacterDisplayViewStateFactory
import com.onats.characters.presentation.factories.CharacterSearchViewStateFactory
import com.onats.characters.state.mvibase.MviState
import com.onats.characters.state.mvibase.ScreenState
import com.onats.core_character.models.CharacterSummary

data class CharacterScreenStates(
    val searchResultScreen: CharacterSearchResultsViewState = CharacterSearchViewStateFactory.initialState,
    val charactersDisplayScreen: CharacterDisplayStates = CharacterDisplayViewStateFactory.initialState
): ScreenState, MviState


data class CharacterSearchResultsViewState(
    val characters: List<CharacterSummary> = listOf(),
    val showSearchDropdown: Boolean = false,
    val showProgress: Boolean = false,
    val error: String = ""
): MviState

data class CharacterDisplayStates(
    val allCharacters: List<CharacterSummary> = listOf(),
    val showProgress: Boolean = true,
    val error: String = ""
): MviState

