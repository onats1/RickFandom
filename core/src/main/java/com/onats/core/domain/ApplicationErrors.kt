package com.onats.core.domain
import androidx.annotation.StringRes

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
        @StringRes val errorMessage: Int,
        val displayMode: UIErrorDisplayMode
    ): ApplicationError(){
        companion object {
            val defaultDisplayMode = UIErrorDisplayMode.TOAST
        }
    }
}

const val DEFAULT_NETWORK_ERROR_CODE = 600 //600 is out of HTTP error code range

enum class UIErrorDisplayMode { SNACKBAR, TOAST , DIALOG }
