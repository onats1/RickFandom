package com.onats.common

sealed class ApplicationError {

    data class NetworkError(
        val errorMessage: String,
        val errorCode: Int? = null,
        val errorException: Exception? = null
    ): ApplicationError() {

        companion object {
            val defaultError = NetworkError(
                errorMessage = "A network Error Occurred. Please try again.",
                errorCode = DEFAULT_NETWORK_ERROR_CODE
            )
        }
    }

    object NoError: ApplicationError()
}

const val DEFAULT_NETWORK_ERROR_CODE = 600 //600 is out of HTTP error code range