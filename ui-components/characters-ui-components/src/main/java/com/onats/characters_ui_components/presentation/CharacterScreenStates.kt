package com.onats.characters_ui_components.presentation.charactercomponentstates

import com.onats.characters_ui_components.presentation.components.characterdisplaycomponent.characterdisplaystates.CharacterDisplayComponentStates
import com.onats.common_ui.presentation.ScreenState

sealed class CharacterScreenStates(
    val characterScreenComponents: CharacterScreenComponents = CharacterScreenComponents()
) : ScreenState {
    object InitialState : CharacterScreenStates()
    data class CharacterQueryComponentState(private val characterQueryComponents: CharacterScreenComponents): CharacterScreenStates(characterQueryComponents)
    data class CharacterDisplayComponentState(private val characterDisplayComponents: CharacterScreenComponents): CharacterScreenStates(characterDisplayComponents)
}

data class CharacterScreenComponents(
    val characterSearchData: CharacterSearchComponentStates = CharacterSearchComponentStates.InitialState,
    val characterData: CharacterDisplayComponentStates = CharacterDisplayComponentStates.InitialState
)

fun CharacterScreenStates.reduceToCharactersQueried(characterSearchData: CharacterSearchComponentStates): CharacterScreenComponents {
    return characterScreenComponents.copy(
        characterSearchData = characterSearchData
    )
}

fun CharacterScreenStates.reduceToCharacterDisplayState(characterData: CharacterDisplayComponentStates): CharacterScreenComponents {
    return characterScreenComponents.copy(
        characterData = characterData
    )
}
