package com.onats.characters_ui_components.presentation.intents

import com.onats.characters_ui_components.presentation.CharacterScreenStates
import com.onats.characters_ui_components.presentation.components.characterdisplaycomponent.characterdisplaystates.CharacterComponentResults
import com.onats.characters_ui_components.presentation.components.characterdisplaycomponent.characterdisplaystates.CharacterComponentStateMachine
import com.onats.characters_ui_components.presentation.components.characterdisplaycomponent.characterdisplaystates.ErrorTypes
import com.onats.characters_ui_components.presentation.components.characterqueryfieldcomponent.characterqueryfieldstates.CharacterQueryComponentStateMachine
import com.onats.characters_ui_components.presentation.components.characterqueryfieldcomponent.characterqueryfieldstates.CharacterQueryFieldResults
import com.onats.characters_ui_components.presentation.components.charactersearchresults.charactersearchresultstate.CharacterSearchComponentResult
import com.onats.characters_ui_components.presentation.components.charactersearchresults.charactersearchresultstate.CharacterSearchComponentStateMachine
import com.onats.characters_ui_components.presentation.components.charactersearchresults.charactersearchresultstate.CharacterSearchErrorTypes
import com.onats.common.DomainResult
import com.onats.common_ui.presentation.MVIIntent
import com.onats.core_character.usecases.GetAllCharactersUseCase
import com.onats.core_character.usecases.SearchCharactersUseCase
import com.onats.core_character.usecases.SearchQueryRequest
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class CharacterIntentProcessor
@Inject
constructor(
    private val getAllCharactersUseCase: GetAllCharactersUseCase,
    private val queryCharactersUseCase: SearchCharactersUseCase
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
                    } else {
                        val charactersErrorState = CharacterComponentStateMachine.transform(
                            CharacterComponentResults.Error(ErrorTypes.NETWORK_ERROR),
                            currentScreenState
                        )
                        updateScreenState(charactersErrorState)
                    }
                }
            }

            is QueryInProgress -> {
                val query = intent.query

                if (query.isEmpty()) {
                    val emptyState = CharacterQueryComponentStateMachine.transform(
                        CharacterQueryFieldResults.EmptyQuery,
                        currentScreenState
                    )
                    updateScreenState(emptyState)
                } else {
                    val queryInProgressState = CharacterQueryComponentStateMachine.transform(
                        CharacterQueryFieldResults.SearchQuery(intent.query),
                        currentScreenState
                    )
                    updateScreenState(queryInProgressState)
                }
            }

            is ExecuteQuery -> {
                val query = intent.query

                val result = CharacterSearchComponentStateMachine.transform(
                    CharacterSearchComponentResult.Loading,
                    currentScreenState
                )
                updateScreenState(result)

                queryCharactersUseCase(SearchQueryRequest(query)).collect { domainResult ->
                    if (domainResult is DomainResult.Success) {
                        val characters = domainResult.data
                        if (characters.isEmpty()) {
                            val state = CharacterSearchComponentStateMachine.transform(
                                CharacterSearchComponentResult.NoMatches,
                                currentScreenState
                            )
                            updateScreenState(state)
                        } else {
                            val state = CharacterSearchComponentStateMachine.transform(
                                CharacterSearchComponentResult.SuccessResult(domainResult.data),
                                currentScreenState
                            )
                            updateScreenState(state)
                        }
                    } else if (domainResult is DomainResult.Error) {
                        val state = CharacterSearchComponentStateMachine.transform(
                            CharacterSearchComponentResult.Error(CharacterSearchErrorTypes.NETWORK_ERROR),
                            currentScreenState
                        )
                        updateScreenState(state)
                    }
                }
            }
        }
    }
}