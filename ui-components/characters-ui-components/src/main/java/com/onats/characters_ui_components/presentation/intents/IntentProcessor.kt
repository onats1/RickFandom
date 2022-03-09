package com.onats.characters_ui_components.presentation.intents

import com.onats.characters_ui_components.presentation.CharacterScreenStates
import com.onats.characters_ui_components.presentation.components.characterdisplaycomponent.characterdisplaystates.CharacterComponentResults
import com.onats.characters_ui_components.presentation.components.characterdisplaycomponent.characterdisplaystates.CharacterComponentStateMachine
import com.onats.characters_ui_components.presentation.components.characterqueryfieldcomponent.characterqueryfieldstates.CharacterQueryComponentStateMachine
import com.onats.characters_ui_components.presentation.components.characterqueryfieldcomponent.characterqueryfieldstates.CharacterQueryFieldResults
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
        currentScreenState: CharacterScreenStates,
        updateScreenState: (CharacterScreenStates) -> Unit
    ) {
        when (intent) {
            LoadCharacters -> {
                val charactersLoadingState = CharacterComponentStateMachine.transform(
                    CharacterComponentResults.Loading,
                    currentScreenState
                )
                updateScreenState(charactersLoadingState)
                getAllCharactersUseCase().collect { domainResult ->
                    if (domainResult is DomainResult.Success) {
                        val charactersLoadedState = CharacterComponentStateMachine.transform(
                            CharacterComponentResults.CharactersLoaded(domainResult.data), currentScreenState)
                        updateScreenState(charactersLoadedState)
                    }
                }
            }

            is QueryCharacters -> {
                val queryInProgressState = CharacterQueryComponentStateMachine.transform(
                    CharacterQueryFieldResults.SearchQuery(intent.query),
                    currentScreenState
                )
                updateScreenState(queryInProgressState)

            }
        }
    }
}