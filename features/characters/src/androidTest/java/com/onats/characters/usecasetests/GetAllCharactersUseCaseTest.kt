package com.onats.characters.usecasetests

import com.onats.common.DomainResult
import com.onats.core_character.usecases.GetAllCharactersUseCase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import com.google.common.truth.Truth.assertThat
import com.google.common.truth.Truth.assertWithMessage


@HiltAndroidTest
class GetAllCharactersUseCaseTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var useCase: GetAllCharactersUseCase

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun test_GetAllCharacters_Successful_returnSuccess() = runBlocking {
        useCase().collect { result ->
            assertThat(result is DomainResult.Success)
            assertThat((result as DomainResult.Success).data.isNotEmpty())
            print(result.data)

        }
    }
}