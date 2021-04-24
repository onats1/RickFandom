package com.onats.core.domain

sealed class ApplicationError {

    data class NetworkError(
        val errorMessage: String? = null,
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

    data class UIError(
        val errorMessage: String,
        val displayMode: UIErrorDisplayMode
    ): ApplicationError(){
        companion object {
            val defaultDisplayMode = UIErrorDisplayMode.TOAST
        }
    }
}

const val DEFAULT_NETWORK_ERROR_CODE = 600 //600 is out of HTTP error code range

enum class UIErrorDisplayMode { SNACKBAR, TOAST , DIALOG }
