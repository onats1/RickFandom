package com.onats.util

import com.onats.core.domain.ApplicationError

sealed class PreResolve<out T : Any>
class Success<out T : Any>(val data: T) : PreResolve<T>()
class Error(val error: ApplicationError.UIError) : PreResolve<ApplicationError.UIError>()

inline fun <T : Any> PreResolve<T>.onSuccess(action: (T) -> Unit): PreResolve<T> {
    if (this is Success) action(data)
    return this
}

inline fun <T : Any> PreResolve<T>.onError(action: (Error) -> Unit): PreResolve<T> {
    if (this is Error) action(this)
    return this
}