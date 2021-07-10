package com.onats.core_android_character.data

import com.onats.core_android_character.CharactersResponse
import retrofit2.Response
import retrofit2.http.GET

interface CharactersApiService {

    @GET("/api/character")
    suspend fun getAllCharacters(): Response<CharactersResponse>
}