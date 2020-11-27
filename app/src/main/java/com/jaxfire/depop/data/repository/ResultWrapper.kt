package com.jaxfire.depop.data.repository

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T): ResultWrapper<T>()
    object NetworkError: ResultWrapper<Nothing>()
    object GenericError: ResultWrapper<Nothing>()
}
