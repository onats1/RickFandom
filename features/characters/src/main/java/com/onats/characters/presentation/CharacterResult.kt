package com.onats.characters.presentation

import com.onats.characters.state.mvibase.MviResult
import com.onats.core_character.models.CharacterSummary

sealed class CharacterResult: MviResult {

    sealed class CharacterSearchResult: CharacterResult() {
        object NoResult: CharacterSearchResult()
        data class SearchResults(val results: List<CharacterSummary>): CharacterSearchResult()
        data class Error(val errorMessage: String): CharacterDisplayResult()
    }

    sealed class CharacterDisplayResult: CharacterResult() {
        object NoCharacters: CharacterDisplayResult()
        data class CharactersRetrieved(val characters: List<CharacterSummary>): CharacterDisplayResult()
        data class DisplayError(val errorMessage: String): CharacterDisplayResult()
    }
}