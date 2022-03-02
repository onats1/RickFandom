package com.onats.characters_ui_components.presentation.characterstates

import com.onats.common_ui.presentation.ScreenState

sealed class CharacterDisplayScreenStates(
    val characterScreenComponents: CharacterScreenData = CharacterScreenData()
) : ScreenState {
    object InitialState : CharacterDisplayScreenStates()
    data class CharacterQueryComponentState(private val characterQueryData: CharacterScreenData): CharacterDisplayScreenStates(characterQueryData)
    data class CharacterDisplayComponentState(private val characterDisplayData: CharacterScreenData): CharacterDisplayScreenStates(characterDisplayData)
}

data class CharacterScreenData(
    val characterSearchData: CharacterSearchComponentStates = CharacterSearchComponentStates.InitialState,
    val characterData: CharacterDisplayComponentStates = CharacterDisplayComponentStates.InitialState
)

fun CharacterDisplayScreenStates.reduceToCharactersQueried(characterSearchData: CharacterSearchComponentStates): CharacterScreenData {
    return characterScreenComponents.copy(
        characterSearchData = characterSearchData
    )
}

fun CharacterDisplayScreenStates.reduceToCharacterDisplayState(characterData: CharacterDisplayComponentStates): CharacterScreenData {
    return characterScreenComponents.copy(
        characterData = characterData
    )
}
