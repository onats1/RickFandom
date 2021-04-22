package com.onats.core.domain.characters

data class Character(
    val name: String,
    val status: String,
    val gender: String,
    val species: String,
    val image: String,
    val isSaved: Boolean
)