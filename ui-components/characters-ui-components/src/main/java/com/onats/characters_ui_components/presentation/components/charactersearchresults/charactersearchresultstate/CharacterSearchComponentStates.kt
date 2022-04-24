package com.onats.characters_ui_components.presentation.components.charactersearchresults.charactersearchresultstate

import com.onats.characters_ui_components.presentation.components.characterdisplaycomponent.characterdisplaystates.ErrorTypes
import com.onats.common_ui.presentation.ComponentState
import com.onats.core_character.models.CharacterSummary

sealed class CharacterSearchComponentStates(val data: CharacterSearchData = CharacterSearchData()) :
    ComponentState {
    object InitialState : CharacterSearchComponentStates()
    data class LoadingState(val currentData: CharacterSearchData) : CharacterSearchComponentStates(currentData)
    data class CharactersSearched(
        val searchData: CharacterSearchData
    ) : CharacterSearchComponentStates(searchData)
}

data class CharacterSearchData(
    val queryResults: List<CharacterSummary> = listOf(),
    val errorType: ErrorTypes = ErrorTypes.NONE
)

fun CharacterSearchComponentStates.reduceToLoadingState(): CharacterSearchData {
    return data
}

fun CharacterSearchComponentStates.reduceToCharactersLoadedState(characters: List<CharacterSummary>): CharacterSearchData {
    return data.copy(
        queryResults = characters
    )
}

fun CharacterSearchComponentStates.reduceToErrorState(errorType: ErrorTypes): CharacterSearchData {
    return data.copy(
        errorType = errorType
    )
}



