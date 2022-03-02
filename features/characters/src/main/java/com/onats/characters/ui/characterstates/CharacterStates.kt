package com.onats.characters.ui.characterstates

import com.onats.common_ui.presentation.ScreenState

sealed class CharacterDisplayScreenStates(
    val characterScreenData: CharacterScreenData = CharacterScreenData()
) : ScreenState {
    object InitialState : CharacterDisplayScreenStates()
    data class CharacterQueryComponentState(val characterQueryData: CharacterScreenData): CharacterDisplayScreenStates(characterQueryData)
    data class CharacterDisplayComponentState(val characterDisplayData: CharacterScreenData): CharacterDisplayScreenStates(characterDisplayData)
}

data class CharacterScreenData(
    val characterSearchData: CharacterSearchComponentStates = CharacterSearchComponentStates.InitialState,
    val characterData: CharacterDisplayComponentStates = CharacterDisplayComponentStates.InitialState
)

fun CharacterDisplayScreenStates.reduceToCharactersQueried(characterSearchData: CharacterSearchComponentStates): CharacterScreenData {
    return characterScreenData.copy(
        characterSearchData = characterSearchData
    )
}

fun CharacterDisplayScreenStates.reduceToCharacterDisplayState(characterData: CharacterDisplayComponentStates): CharacterScreenData {
    return characterScreenData.copy(
        characterData = characterData
    )
}
