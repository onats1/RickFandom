package com.onats.characters.presentation.factories

import com.onats.characters.presentation.CharacterSearchResultsViewState
import com.onats.core_character.models.CharacterSummary

//Takes the existing state of search results to transform to state

inline fun CharacterSearchResultsViewState.state(
    transform: CharacterSearchViewStateFactory.() -> CharacterSearchResultsViewState
) = transform(CharacterSearchViewStateFactory(this))

object CharacterSearchViewStateFactory {

    private lateinit var state: CharacterSearchResultsViewState

    operator fun invoke(state: CharacterSearchResultsViewState): CharacterSearchViewStateFactory {
        this.state = state
        return this
    }

    val initialState
        get() = CharacterSearchResultsViewState()

    val beginSearch
        get() = state.copy(
            showSearchDropdown = true
        )

    val hide
        get() = state.copy(
            showSearchDropdown = false
        )

    fun noResult(): CharacterSearchResultsViewState = state.copy(
        characters = listOf(),
        showProgress = false,
    )

    fun charactersLoaded(characters: List<CharacterSummary>): CharacterSearchResultsViewState = state.copy(
        characters = characters,
        showProgress = false
    )

    fun error(message: String): CharacterSearchResultsViewState = state.copy(
            error = message
        )
}
