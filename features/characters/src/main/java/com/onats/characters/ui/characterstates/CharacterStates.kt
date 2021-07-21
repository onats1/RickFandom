package com.onats.characters.ui.characterstates

import com.onats.core_character.models.CharacterSummary

sealed class CharacterDisplayStates {
    object InitialState: CharacterDisplayStates()
    object LoadingState: CharacterDisplayStates()
    data class CharactersLoaded(val characters: List<CharacterSummary>): CharacterDisplayStates()
    data class Error(val errorMessage: String): CharacterDisplayStates()
}