package com.onats.characters_domain

import com.google.common.truth.Truth.assertThat
import com.onats.characters_domain.repository.CharactersRepositoryImpl
import com.onats.common.ApplicationError
import com.onats.common.DomainResult
import com.onats.common.Result
import com.onats.core_character.data.CharactersRemoteDataSource
import com.onats.core_character.models.Character
import com.onats.core_character.models.Location
import com.onats.core_character.models.Origin
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock


@ExperimentalStdlibApi
class CharactersRepositoryTest {

    private val mockCharacterDataSource = mock(CharactersRemoteDataSource::class.java)

    @Test
    fun `test repository success state returns Domain success with list`() = runBlocking {
        `when`(mockCharacterDataSource.getAllCharacters()).thenAnswer {
            flow<Result<List<Character>>> {
                emit(
                    Result.data(
                        data = fakeCharacterList
                    )
                )
            }
        }
        val characterRepo = CharactersRepositoryImpl(
            remoteDataSource = mockCharacterDataSource
        )
        characterRepo.getAllCharacters().collect { domainResult ->
            assertThat(domainResult).isInstanceOf(DomainResult.Success::class.java)
            assertThat((domainResult as DomainResult.Success).data).isEqualTo(fakeCharacterList)
        }
    }

    @Test
    fun `test repository error state returns domain error`() = runBlocking {
        `when`(mockCharacterDataSource.getAllCharacters()).thenAnswer {
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
        characterRepo.getAllCharacters().collect { domainResult ->
            assertThat(domainResult).isInstanceOf(DomainResult.Error::class.java)
        }
    }
}

@ExperimentalStdlibApi
private val fakeCharacterList = buildList<Character> {
    for (x in 0..9) {
        add(
            Character(
                id = 1,
                name = "Test Rick",
                status = "Alive",
                species = "Human",
                gender = "Male",
                origin = Origin.NO_ORIGIN,
                location = Location.NO_LOCATION,
                episodes = listOf(),
                image = "",
                url = "",
                created = ""
            )
        )
    }
}