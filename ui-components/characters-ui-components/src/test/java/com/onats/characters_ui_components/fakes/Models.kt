package com.onats.characters_ui_components.fakes

import com.onats.characters_ui_components.presentation.components.demoCharacter
import com.onats.core_character.models.Character
import com.onats.core_character.models.CharacterSummary

val testCharacters: List<CharacterSummary> = mutableListOf<CharacterSummary>().apply {
    for (i in 0 until 20) {
        add(demoCharacter)
    }
}
