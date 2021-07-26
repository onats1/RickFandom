package com.onats.characters.presentation.intentprocessors

sealed interface CharactersIntent: ViewIntents {
    object GetAllCharactersIntent: CharactersIntent
    data class CharacterSearchIntent(val query: String): CharactersIntent
}