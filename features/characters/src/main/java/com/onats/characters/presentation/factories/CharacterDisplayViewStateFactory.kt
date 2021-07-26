package com.onats.characters.presentation.factories

import com.onats.characters.presentation.CharacterDisplayStates
import com.onats.core_character.models.CharacterSummary

inline fun CharacterDisplayStates.state(
    transform: CharacterDisplayViewStateFactory.() -> CharacterDisplayStates
): CharacterDisplayStates = transform(CharacterDisplayViewStateFactory(this))

object CharacterDisplayViewStateFactory {

    private lateinit var state: CharacterDisplayStates

    operator fun invoke(
        initState: CharacterDisplayStates
    ): CharacterDisplayViewStateFactory {
        state = initState
        return this
    }

    val initialState
        get() = CharacterDisplayStates()

    fun loadedState(characters: List<CharacterSummary>): CharacterDisplayStates = state.copy(
        allCharacters = characters,
        showProgress = false
    )

    fun noCharacters(): CharacterDisplayStates = state.copy(
        allCharacters = listOf(),
        showProgress = false,
        error = "No Characters at the moment."
    )

    val loadingState: CharacterDisplayStates
        get() = state.copy(
            showProgress = true
        )

    fun errorState(errorMessage: String): CharacterDisplayStates = state.copy(
        allCharacters = listOf(),
        showProgress = false,
        error = errorMessage
    )
}