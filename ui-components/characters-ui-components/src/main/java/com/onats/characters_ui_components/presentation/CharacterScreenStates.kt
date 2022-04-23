package com.onats.characters_ui_components.presentation

import com.onats.characters_ui_components.presentation.components.characterdisplaycomponent.characterdisplaystates.CharacterDisplayComponentStates
import com.onats.characters_ui_components.presentation.components.charactersearchresults.charactersearchresultstate.CharacterSearchComponentStates
import com.onats.characters_ui_components.presentation.components.characterqueryfieldcomponent.characterqueryfieldstates.CharacterQueryFieldComponentStates
import com.onats.common_ui.presentation.ScreenState

sealed class CharacterScreenStates(
    val characterScreenComponents: CharacterScreenComponents = CharacterScreenComponents()
) : ScreenState {
    object InitialState : CharacterScreenStates()
    data class CharacterSearchResultsState(private val characterQueryComponents: CharacterScreenComponents): CharacterScreenStates(characterQueryComponents)
    data class CharacterQueryFieldComponentState(private val characterQueryFieldComponent: CharacterScreenComponents): CharacterScreenStates(characterQueryFieldComponent)
    data class CharacterDisplayComponentState(private val characterDisplayComponents: CharacterScreenComponents): CharacterScreenStates(characterDisplayComponents)
}

data class CharacterScreenComponents(
    val characterSearchData: CharacterSearchComponentStates = CharacterSearchComponentStates.InitialState,
    val characterData: CharacterDisplayComponentStates = CharacterDisplayComponentStates.InitialState,
    val characterQueryFieldData: CharacterQueryFieldComponentStates = CharacterQueryFieldComponentStates.InitialState
)

fun CharacterScreenStates.reduceToCharactersQueriedState(characterSearchData: CharacterSearchComponentStates): CharacterScreenComponents {
    return characterScreenComponents.copy(
        characterSearchData = characterSearchData
    )
}

fun CharacterScreenStates.reduceToCharacterDisplayState(characterData: CharacterDisplayComponentStates): CharacterScreenComponents {
    return characterScreenComponents.copy(
        characterData = characterData
    )
}

fun CharacterScreenStates.reduceToCharacterQueryDisplayState(characterQueryFieldData: CharacterQueryFieldComponentStates): CharacterScreenComponents {
    return characterScreenComponents.copy(
        characterQueryFieldData = characterQueryFieldData
    )
}
