package com.onats.characters_ui_components.presentation.charactercomponentstates

import com.onats.characters_ui_components.presentation.charactercomponentstates.characterdisplaystates.ErrorTypes
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
    val query: String = "",
    val queryResults: List<CharacterSummary> = listOf(),
    val errorType: ErrorTypes = ErrorTypes.NONE
)

fun CharacterSearchComponentStates.reduceToCharactersLoadedState(query: String, characters: List<CharacterSummary>): CharacterSearchData {
    return data.copy(
        query = query,
        queryResults = characters
    )
}

fun CharacterSearchComponentStates.reduceToErrorState(errorType: ErrorTypes): CharacterSearchData {
    return data.copy(
        errorType = errorType
    )
}



