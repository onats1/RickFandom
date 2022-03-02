package com.onats.characters.ui.intents

import com.onats.characters.ui.characterstates.CharacterDisplayComponentStates
import com.onats.characters.ui.characterstates.CharacterDisplayScreenStates
import com.onats.characters.ui.characterstates.reduceToCharacterDisplayState
import com.onats.characters.ui.characterstates.reduceToCharactersLoadedState
import com.onats.common_ui.presentation.MVIIntent

object CharacterIntentProcessor {

    fun invoke(
        intent: MVIIntent,
        currentScreenState: CharacterDisplayScreenStates,
        updateScreenState: (CharacterDisplayScreenStates) -> Unit
    ) {

        when (intent) {
            LoadCharacters -> {
                if (currentScreenState is CharacterDisplayScreenStates.CharacterDisplayComponentState) {
                    val characterDisplayComponent = currentScreenState.characterDisplayData.characterData
                    // query data
                    val updatedCharacterDisplayData = characterDisplayComponent.reduceToCharactersLoadedState(listOf())
                    val updatedCharacterDisplayComponent = CharacterDisplayComponentStates.CharactersLoaded(updatedCharacterDisplayData)
                    val updatedScreenState = currentScreenState.reduceToCharacterDisplayState(updatedCharacterDisplayComponent)
                    updateScreenState(CharacterDisplayScreenStates.CharacterDisplayComponentState(updatedScreenState))
                }
            }
        }
    }
}