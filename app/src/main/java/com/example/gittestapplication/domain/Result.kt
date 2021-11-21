package com.example.gittestapplication.domain

import java.lang.Exception

sealed class Result<out T> {
    companion object {
        inline fun <T> create(block: () -> T) = runCatching {
            val result = block() ?: throw Exception()
            Success(result)
        }.getOrElse {
            Failure(it)
        }
    }

    data class Success<T>(val value: T) : Result<T>()
    data class Failure<T>(val throwable: Throwable) : Result<T>()
}