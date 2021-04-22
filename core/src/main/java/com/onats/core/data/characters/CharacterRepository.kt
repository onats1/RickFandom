package com.onats.core.data.characters

import com.onats.core.domain.characters.Character

interface CharacterRepository {

    fun getAllCharacters(): List<Character>
}