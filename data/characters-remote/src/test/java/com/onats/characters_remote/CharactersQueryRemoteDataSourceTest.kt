package com.onats.characters_remote

import com.google.common.truth.Truth
import com.onats.characters_remote.data.CharactersRemoteDataSourceImpl
import com.onats.characters_remote.mappers.CharacterMapperImpl
import com.onats.characters_remote.mappers.LocationMapperImpl
import com.onats.characters_remote.mappers.OriginMapperImpl
import com.onats.common.ApplicationError
import com.onats.core_android_character.CharactersResponse
import com.onats.core_android_character.data.CharactersApiService
import com.onats.core_character.models.Character
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import retrofit2.Response
import java.io.IOException

@ExperimentalStdlibApi
class CharactersQueryRemoteDataSourceTest {

    private val mockApiService = mock(CharactersApiService::class.java)
    private val mockCharacterMapper = CharacterMapperImpl(
        originMapper = OriginMapperImpl(),
        locationMapper = LocationMapperImpl()
    )

    @Test
    fun `test that successful query returns list of characters`() = runBlocking {
        `when`(mockApiService.queryCharacterByName("")).thenAnswer {
            Response.success<CharactersResponse>(
                characterNetworkResponse
            )
        }
        val dataSource = CharactersRemoteDataSourceImpl(
            charactersApiService = mockApiService,
            charactersMapper = mockCharacterMapper
        )
        val characterList = mockCharacterMapper.mapToDomainList(fakeCharacterDtoList)
        dataSource.queryCharacter("").collect { result ->
            Truth.assertThat(result.error).isEqualTo(ApplicationError.NoError)
            Truth.assertThat(result.data).isEqualTo(characterList)
        }
    }

    @Test
    fun `test that error response returns application network error`() = runBlocking {
        `when`(mockApiService.queryCharacterByName("")).thenAnswer {
            Response.error<List<Character>>(
                401, "Page not found"
                    .toResponseBody("application/json".toMediaTypeOrNull())
            )
        }
        val dataSource = CharactersRemoteDataSourceImpl(
            charactersApiService = mockApiService,
            charactersMapper = mockCharacterMapper
        )
        dataSource.queryCharacter("").collect { characterResult ->
            Truth.assertThat(characterResult.error).isInstanceOf(ApplicationError.NetworkError::class.java)
            Truth.assertThat(characterResult.data).isNull()
        }
    }

    @Test
    fun `test that 404 error returns empty list`() = runBlocking {
        `when`(mockApiService.queryCharacterByName("")).thenAnswer {
            Response.error<List<Character>>(
                404, "Page not found"
                    .toResponseBody("application/json".toMediaTypeOrNull())
            )
        }
        val dataSource = CharactersRemoteDataSourceImpl(
            charactersApiService = mockApiService,
            charactersMapper = mockCharacterMapper
        )
        dataSource.queryCharacter("").collect { characterResult ->
            Truth.assertThat(characterResult.error).isInstanceOf(ApplicationError.NoError::class.java)
            Truth.assertThat(characterResult.data).isNotNull()
            characterResult.data?.let {
                assert(it.isEmpty())
            }
        }
    }

    @Test
    fun `test that network exception is caught as network exception error`() = runBlocking {
        `when`(mockApiService.queryCharacterByName("")).thenAnswer {
            throw IOException()
        }
        val dataSource = CharactersRemoteDataSourceImpl(
            charactersApiService = mockApiService,
            charactersMapper = mockCharacterMapper
        )

        dataSource.queryCharacter("").collect { characterResult ->
            Truth.assertThat(characterResult.error).isInstanceOf(ApplicationError.NetworkError::class.java)
            Truth.assertThat(characterResult.data).isNull()
            Truth.assertThat((characterResult.error as ApplicationError.NetworkError).errorException)
                .isNotNull()
        }
    }
}