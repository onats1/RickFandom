package com.onats.characters_ui_components.fakes

import com.onats.characters_ui_components.components.demoCharacterSummary
import com.onats.core_character.models.Character
import com.onats.core_character.models.CharacterSummary
import com.onats.core_character.models.Location
import com.onats.core_character.models.Origin

val testCharactersSummary: List<CharacterSummary> = mutableListOf<CharacterSummary>().apply {
    for (i in 0 until 20) {
        add(demoCharacterSummary)
    }
}

val testCharacter: Character = Character(
    id = 0,
    name = "",
    status = "",
    species = "",
    gender = "",
    origin = Origin.NO_ORIGIN,
    location = Location.NO_LOCATION,
    image = "",
    episodes = listOf(),
    url = "",
    created = ""
)

val testCharacters: List<Character> = mutableListOf<Character>().apply {
    for (i in 0 until 20){
        add(testCharacter)
    }
}

