package com.onats.util

import com.onats.core.domain.ApplicationError

interface ErrorMapper<in NetworkError: ApplicationError, out UIError: ApplicationError> {

    fun getDisplayError(networkError: NetworkError): UIError
}