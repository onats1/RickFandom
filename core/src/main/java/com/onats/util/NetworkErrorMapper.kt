package com.onats.util

import com.onats.core.characters.util.ApplicationError

interface ErrorMapper<in NetworkError: ApplicationError, out UIError: ApplicationError> {

    fun getDisplayError(networkError: NetworkError): UIError
}