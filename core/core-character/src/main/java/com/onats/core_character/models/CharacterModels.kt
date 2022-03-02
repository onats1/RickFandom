package com.onats.core_character.models

data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val origin: Origin,
    val location: Location,
    val image: String,
    val episodes: List<String>,
    val url: String,
    val created: String,
    val summary: CharacterSummary = CharacterSummary(
        id = id,
        name = name,
        gender = gender,
        species = species,
        image = image,
        episodes = episodes.size,
        location = location.name
    )
)

data class Origin(
    val name: String,
    val url: String,
) {
    companion object {
        val NO_ORIGIN = Origin("", "")
    }
}

data class Location(
    val name: String,
    val url: String,
) {
    companion object {
        val NO_LOCATION = Location("", "")
    }
}

data class CharacterSummary(
    val id: Int,
    val name: String,
    val gender: String,
    val species: String,
    val image: String,
    val episodes: Int,
    val location: String
)