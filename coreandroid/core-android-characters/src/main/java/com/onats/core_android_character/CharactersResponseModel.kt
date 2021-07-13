package com.onats.core_android_character

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharactersResponse(
    val info: Info?,
    val results: List<CharacterDto>?
)

@JsonClass(generateAdapter = true)
data class CharacterDto(
    val id: Int?,
    val name: String?,
    val status: String?,
    val species: String?,
    val type: String? = null,
    val gender: String?,
    val origin: OriginDto?,
    val location: LocationDto?,
    val image: String?,
    val episode: List<String>?,
    val url: String?,
    val created: String?
)

@JsonClass(generateAdapter = true)
data class OriginDto(
    val name: String?,
    val url: String?,
)

@JsonClass(generateAdapter = true)
data class LocationDto(
    val name: String?,
    val url: String?,
)

@JsonClass(generateAdapter = true)
data class Info(
    val count: Int?,
    val next: String?,
    val pages: Int?,
    val prev: String?
)