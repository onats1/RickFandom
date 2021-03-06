package com.onats.characters_ui_components.presentation.components.characterdisplaycomponent.characterdisplaystates

import androidx.annotation.StringRes
import com.onats.characters_ui_components.R
import com.onats.common_ui.presentation.ComponentState
import com.onats.core_character.models.CharacterSummary

sealed class CharacterDisplayComponentStates(val data: CharacterDisplayData = CharacterDisplayData()) :
    ComponentState {
    object InitialState: CharacterDisplayComponentStates()
    data class LoadingState(val characterData: CharacterDisplayData) : CharacterDisplayComponentStates(characterData)
    data class ExistingState(val characterData: CharacterDisplayData): CharacterDisplayComponentStates(characterData)
    data class CharactersLoaded(val characterData: CharacterDisplayData) :
        CharacterDisplayComponentStates(characterData)
    data class ErrorState(val characterData: CharacterDisplayData): CharacterDisplayComponentStates(characterData)
}

data class CharacterDisplayData(
    val characters: List<CharacterSummary> = listOf(),
    val errorData: ErrorTypes = ErrorTypes.NONE
)

enum class ErrorTypes(@StringRes val errorMessage: Int) {
    NONE(R.string.no_error),
    NETWORK_ERROR(R.string.network_error_occured)
}

fun CharacterDisplayComponentStates.reduceToCharactersLoadedState(characters: List<CharacterSummary>): CharacterDisplayData {
    return data.copy(
        characters = characters
    )
}

fun CharacterDisplayComponentStates.reduceToLoadingState(): CharacterDisplayData {
    return data
}

/**
 * Provides the already existing data set for manipulation before processing and returning the response
 * */
typealias CharacterUpdateFilter = (List<CharacterSummary>) -> List<CharacterSummary>

infix fun CharacterDisplayComponentStates.reduceToCharactersLoadedState(oldCharactersFilter: CharacterUpdateFilter): CharacterDisplayData {
    val oldCharacterList = data.characters
    val updatedList = oldCharactersFilter(oldCharacterList)
    return data.copy(
        characters = updatedList
    )
}

fun CharacterDisplayComponentStates.reduceToErrorState(error: ErrorTypes): CharacterDisplayData {
    return data.copy(
        errorData = error
    )
}