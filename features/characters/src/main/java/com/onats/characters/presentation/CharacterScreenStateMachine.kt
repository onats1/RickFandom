package com.onats.characters.presentation

import com.onats.characters.presentation.CharacterResult.CharacterDisplayResult.*
import com.onats.characters.presentation.CharacterResult.CharacterSearchResult.*
import com.onats.characters.presentation.factories.translate
import com.onats.characters.state.mvibase.MviStateMachine

class CharacterScreenStateMachine : MviStateMachine<CharacterScreenStates, CharacterResult> {

    override fun CharacterScreenStates.reduceTo(result: CharacterResult): CharacterScreenStates {
        return when (result) {
            //Search
            is SearchResults -> this.translate {
                characterSearchState { charactersLoaded(result.results) }
            }
            is Error -> this.translate {
                characterSearchState {
                    error(message = result.errorMessage)
                }
            }
            NoResult -> this.translate { characterSearchState {
                noResult()
            } }

            //Display
            is CharactersRetrieved -> this.translate {
                characterDisplayState { loadedState(result.characters) }
            }

            is NoCharacters -> this.translate {
                characterDisplayState { noCharacters() }
            }

            is DisplayError -> this.translate {
                characterDisplayState { errorState(result.errorMessage) }
            }

        }
    }
}