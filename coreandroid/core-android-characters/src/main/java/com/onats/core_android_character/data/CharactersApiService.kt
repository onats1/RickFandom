package com.onats.core_android_character.data

import com.onats.core_android_character.CharactersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CharactersApiService {

    @GET("/api/character")
    suspend fun getAllCharacters(): Response<CharactersResponse>

    @GET("/api/character")
    suspend fun queryCharacterByName(
        @Query("name") param: String
    ): Response<CharactersResponse>
}