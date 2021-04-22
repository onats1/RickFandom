package com.onats.core.characters.util

data class DataState<T, E: ApplicationError>(
    val error: E?,
    val data: T?
) {
    companion object {
        fun <T> error(
            error: ApplicationError
        ): DataState<T, ApplicationError> {
            return DataState(
                error = error,
                data = null
            )
        }

        fun <T> data(
            data: T
        ): DataState<T, ApplicationError> {
            return DataState(
                error = null,
                data = data
            )
        }
    }
}