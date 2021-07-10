package com.onats.characters_remote

import com.google.common.truth.Truth.assertThat
import com.onats.characters_remote.data.CharactersRemoteDataSourceImpl
import com.onats.characters_remote.mappers.CharacterMapperImpl
import com.onats.characters_remote.mappers.LocationMapperImpl
import com.onats.characters_remote.mappers.OriginMapperImpl
import com.onats.common.ApplicationError
import com.onats.core_android_character.CharacterDto
import com.onats.core_android_character.CharactersResponse
import com.onats.core_android_character.LocationDto
import com.onats.core_android_character.OriginDto
import com.onats.core_android_character.data.CharactersApiService
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import retrofit2.Response

@ExperimentalStdlibApi
class CharactersRemoteDataSourceTest {

    private val mockApiService = mock(CharactersApiService::class.java)
    private val mockCharacterMapper = CharacterMapperImpl(
        originMapper = OriginMapperImpl(),
        locationMapper = LocationMapperImpl()
    )



    @Test
    fun `test that success response returns list of characters`() = runBlocking {
        `when`(mockApiService.getAllCharacters()).thenAnswer {
            return@thenAnswer Response.success<CharactersResponse>(
                characterNetworkResponse
            )
        }
        val dataSource = CharactersRemoteDataSourceImpl(
            charactersApiService = mockApiService,
            charactersMapper = mockCharacterMapper
        )
        val characterList = mockCharacterMapper.mapToDomainList(fakeCharacterDtoList)

        dataSource.getAllCharacters().collect { result ->
            assertThat(result.error).isEqualTo(ApplicationError.NoError)
            assertThat(result.data).isEqualTo(characterList)
        }

    }
}

@ExperimentalStdlibApi
private val characterNetworkResponse by lazy {
    CharactersResponse(
        info = null,
        results = fakeCharacterDtoList
    )
}

@ExperimentalStdlibApi
private val fakeCharacterDtoList = buildList<CharacterDto> {
    for (x in 0..9) {
        add(
            CharacterDto(
                id = 1,
                name = "Test Rick",
                status = "Alive",
                species = "Human",
                gender = "Male",
                origin = OriginDto(null, null),
                location = LocationDto(null, null),
                episode = listOf(),
                image = "",
                url = "",
                created = ""
            )
        )
    }
}







