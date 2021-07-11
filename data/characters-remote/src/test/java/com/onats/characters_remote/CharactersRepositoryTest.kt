package com.onats.characters_remote

import com.onats.common.Result
import com.onats.core_character.data.CharactersRemoteDataSource
import com.onats.core_character.models.Character
import com.onats.core_character.models.Location
import com.onats.core_character.models.Origin
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
            return@thenAnswer Result.data(
                data = fakeCharacterList
            )
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