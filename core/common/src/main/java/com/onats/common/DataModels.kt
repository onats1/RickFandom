package com.onats.common

import kotlinx.coroutines.flow.Flow

data class Result<out T>(
    val error: ApplicationError = ApplicationError.NoError,
    val data: T? = null
) {
    companion object {
        fun <T> data(
            data: T
        ): Result<T> {
            return Result(
                data = data
            )
        }

        fun <T> error(
            error: ApplicationError
        ): Result<T> {
            return Result(
                error = error,
            )
        }
    }
}

sealed class SimpleResultState {
    object Success: SimpleResultState()
    data class Error(val error: ApplicationError): SimpleResultState()
}

sealed class DomainResult<T> {
    data class Success<T>(val data: T) : DomainResult<T>()
    data class Error<T>(val error: ApplicationError) : DomainResult<T>()
}

interface UseCase<T> {
    suspend operator fun invoke(): Flow<DomainResult<T>>
}

interface UseCaseWithParam<in P, T> {

    suspend operator fun invoke(request: P): Flow<DomainResult<T>>
}