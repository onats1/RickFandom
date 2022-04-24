package com.onats.characters_ui_components.uitests

import com.onats.characters_ui_components.presentation.components.characterdisplaycomponent.characterdisplaystates.CharacterDisplayComponentStates
import com.onats.characters_ui_components.presentation.components.characterdisplaycomponent.characterdisplaystates.CharacterDisplayData
import com.onats.characters_ui_components.presentation.components.charactersearchresults.charactersearchresultstate.CharacterSearchComponentStates
import com.onats.characters_ui_components.presentation.components.charactersearchresults.charactersearchresultstate.CharacterSearchData
import com.onats.core_character.models.CharacterSummary

val testCharacterSummary = CharacterSummary(
    id = 3,
    name = "testCharacter",
    episodes = 5,
    species = "Rhinotest",
    gender = "minion",
    location = "",
    image = ""
)


val characters: List<CharacterSummary>
    get() {
        val list = mutableListOf<CharacterSummary>()
        repeat(20) {
            list.add(
                testCharacterSummary
            )
        }
        return list
    }

@ExperimentalStdlibApi
val charactersLoadedState = CharacterDisplayComponentStates.CharactersLoaded(CharacterDisplayData(
    characters = characters
))

val loadingTestState = CharacterDisplayComponentStates.LoadingState(CharacterDisplayData())

val characterSearchLoadingState = CharacterSearchComponentStates.LoadingState(CharacterSearchData())

val charactersSearchLoadedState = CharacterSearchComponentStates.CharactersSearched(CharacterSearchData(
    characters
))

val charactersErrorState = CharacterDisplayComponentStates.ErrorState(CharacterDisplayData())