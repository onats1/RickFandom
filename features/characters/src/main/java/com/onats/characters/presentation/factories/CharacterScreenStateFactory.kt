package com.onats.characters.presentation.factories

import com.onats.characters.presentation.CharacterDisplayStates
import com.onats.characters.presentation.CharacterScreenStates
import com.onats.characters.presentation.CharacterSearchResultsViewState

inline fun CharacterScreenStates.translate(
    transform: CharacterScreenStateFactory.() -> CharacterScreenStates
) = transform(CharacterScreenStateFactory(this))

object CharacterScreenStateFactory {

    private lateinit var state: CharacterScreenStates

    operator fun invoke(
        oldState: CharacterScreenStates
    ): CharacterScreenStateFactory {
        state = oldState
        return this
    }

    val initialState: CharacterScreenStates
        get() = CharacterScreenStates()

    fun characterSearchState(
        transform: CharacterSearchViewStateFactory.() -> CharacterSearchResultsViewState
    ): CharacterScreenStates = state.copy(
        searchResultScreen = state.searchResultScreen.state(transform),
        charactersDisplayScreen = state.charactersDisplayScreen.state { initialState }
    )

    fun characterDisplayState(
        transform: CharacterDisplayViewStateFactory.() -> CharacterDisplayStates
    ): CharacterScreenStates = state.copy(
        searchResultScreen = state.searchResultScreen.state { hide },
        charactersDisplayScreen = state.charactersDisplayScreen.state(transform)
    )
}