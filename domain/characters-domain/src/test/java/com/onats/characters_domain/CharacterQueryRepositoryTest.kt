package com.onats.characters_domain

import com.google.common.truth.Truth.assertThat
import com.onats.characters_domain.repository.CharactersRepositoryImpl
import com.onats.common.ApplicationError
import com.onats.common.DomainResult
import com.onats.common.Result
import com.onats.core_character.data.CharactersRemoteDataSource
import com.onats.core_character.models.Character
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

@ExperimentalStdlibApi
class CharacterQueryRepositoryTest {

    private val mockCharacterDataSource = mock(CharactersRemoteDataSource::class.java)

    @Test
    fun `test that successful data result returns success`() = runBlocking {
        `when`(mockCharacterDataSource.queryCharacter("")).thenAnswer {
            flow<Result<List<Character>>> {
                emit(
                    Result.data<List<Character>>(
                        data = fakeCharacterList
                    )
                )
            }
        }
        val repository = CharactersRepositoryImpl(
            remoteDataSource = mockCharacterDataSource
        )
        repository.searchCharacters("").collect { domainResult ->
            assertThat(domainResult).isInstanceOf(DomainResult.Success::class.java)
        }
    }

    @Test
    fun `test that error result returns error`() = runBlocking {
        `when`(mockCharacterDataSource.queryCharacter("")).thenAnswer {
            flow<Result<List<Character>>> {
                emit(
                    Result.error(
                        error = ApplicationError.NetworkError.defaultError
                    )
                )
            }
        }

        val characterRepo = CharactersRepositoryImpl(
            remoteDataSource = mockCharacterDataSource
        )
        characterRepo.searchCharacters("").collect { domainResult ->
            assertThat(domainResult).isInstanceOf(DomainResult.Error::class.java)
        }
    }
}