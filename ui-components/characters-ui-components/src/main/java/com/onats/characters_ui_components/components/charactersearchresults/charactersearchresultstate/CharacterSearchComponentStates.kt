package com.onats.characters_ui_components.components.charactersearchresults.charactersearchresultstate

import androidx.annotation.StringRes
import com.onats.characters_ui_components.R
import com.onats.common_ui.presentation.ComponentState
import com.onats.core_character.models.CharacterSummary

sealed class CharacterSearchComponentStates(val data: CharacterSearchData = CharacterSearchData()) :
    ComponentState {
    object InitialState : CharacterSearchComponentStates()
    data class LoadingState(val currentData: CharacterSearchData) : CharacterSearchComponentStates(currentData)
    data class CharactersSearched(
        val searchData: CharacterSearchData
    ) : CharacterSearchComponentStates(searchData)
    data class ErrorState(val searchData: CharacterSearchData): CharacterSearchComponentStates(searchData)
}

data class CharacterSearchData(
    val queryResults: List<CharacterSummary> = listOf(),
    val errorType: CharacterSearchErrorTypes = CharacterSearchErrorTypes.NONE
)

enum class CharacterSearchErrorTypes(@StringRes val errorMessage: Int) {
    NONE(R.string.no_error),
    NO_MATCHING_RESULTS(R.string.no_matching_result),
    NETWORK_ERROR(R.string.network_error_occured)
}

fun CharacterSearchComponentStates.reduceToLoadingState(): CharacterSearchData {
    return data
}

fun CharacterSearchComponentStates.reduceToCharactersLoadedState(characters: List<CharacterSummary>): CharacterSearchData {
    return data.copy(
        queryResults = characters
    )
}

fun CharacterSearchComponentStates.reduceToErrorState(errorType: CharacterSearchErrorTypes): CharacterSearchData {
    return data.copy(
        errorType = errorType
    )
}



