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
import com.onats.core_character.models.Character
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import retrofit2.Response
import java.io.IOException

@ExperimentalStdlibApi
internal class CharactersRemoteDataSourceTest {

    private val mockApiService = mock(CharactersApiService::class.java)
    private val mockCharacterMapper = CharacterMapperImpl(
        originMapper = OriginMapperImpl(),
        locationMapper = LocationMapperImpl()
    )



    @Test
    fun `test that success response returns list of characters`() = runBlocking {
        `when`(mockApiService.getAllCharacters()).thenAnswer {
            Response.success<CharactersResponse>(
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

    @Test
    fun `test that error response returns application network error`() = runBlocking {
        `when`(mockApiService.getAllCharacters()).thenAnswer {
             Response.error<List<Character>>(
                404, "Page not found"
                    .toResponseBody("application/json".toMediaTypeOrNull())
            )
        }
        val dataSource = CharactersRemoteDataSourceImpl(
            charactersApiService = mockApiService,
            charactersMapper = mockCharacterMapper
        )

        dataSource.getAllCharacters().collect { characterResult ->
            assertThat(characterResult.error).isInstanceOf(ApplicationError.NetworkError::class.java)
            assertThat(characterResult.data).isNull()
        }
    }

    @Test
    fun `test that network exception is caught as network exception error`() = runBlocking {
        `when`(mockApiService.getAllCharacters()).thenAnswer {
            throw IOException()
        }
        val dataSource = CharactersRemoteDataSourceImpl(
            charactersApiService = mockApiService,
            charactersMapper = mockCharacterMapper
        )

        dataSource.getAllCharacters().collect { characterResult ->
            assertThat(characterResult.error).isInstanceOf(ApplicationError.NetworkError::class.java)
            assertThat(characterResult.data).isNull()
            assertThat((characterResult.error as ApplicationError.NetworkError).errorException).isNotNull()
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







