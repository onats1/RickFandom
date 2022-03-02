package com.onats.characters_ui_components.presentation.intents

import com.onats.characters_ui_components.presentation.characterstates.*
import com.onats.common.DomainResult
import com.onats.common_ui.presentation.MVIIntent
import com.onats.core_character.usecases.GetAllCharactersUseCase
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class CharacterIntentProcessor
@Inject
constructor(
    private val getAllCharactersUseCase: GetAllCharactersUseCase
) {

    suspend fun processIntent(
        intent: MVIIntent,
        currentScreenState: CharacterDisplayScreenStates,
        updateScreenState: (CharacterDisplayScreenStates) -> Unit
    ) {
        when (intent) {
            LoadCharacters -> {
                val characterDisplayComponent = currentScreenState.characterScreenData.characterData
                val characterDisplayComponentState =
                    CharacterDisplayComponentStates.LoadingState(
                        characterDisplayComponent.reduceToLoadingState()
                    )
                val loadingScreenState = currentScreenState.reduceToCharacterDisplayState(
                    characterDisplayComponentState
                )
                updateScreenState(
                    CharacterDisplayScreenStates.CharacterDisplayComponentState(
                        loadingScreenState
                    )
                )
                getAllCharactersUseCase().collect { domainResult ->
                    if (domainResult is DomainResult.Success) {
                        val updatedCharacterDisplayData =
                            characterDisplayComponent.reduceToCharactersLoadedState(domainResult.data.map { it.summary })
                        val updatedCharacterDisplayComponent =
                            CharacterDisplayComponentStates.CharactersLoaded(
                                updatedCharacterDisplayData
                            )
                        val updatedScreenState = currentScreenState.reduceToCharacterDisplayState(
                            updatedCharacterDisplayComponent
                        )
                        updateScreenState(
                            CharacterDisplayScreenStates.CharacterDisplayComponentState(
                                updatedScreenState
                            )
                        )
                    }
                }
            }
        }
    }
}