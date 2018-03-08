package com.jmcaldera.domain.functional

/**
 * Created by jmcaldera on 26-02-18.
 */
sealed class Result<out A, out E> {
    companion object
}

data class Failure<out E>(val error: E) : Result<Nothing, E>()
data class Success<out A>(val value: A) : Result<A, Nothing>()

fun <A> Result.Companion.pure(a: A): Result<A, Nothing> = Success(a)

// Functor
fun <A, E, B> Result<A, E>.map(block: (A) -> B): Result<B, E> = flatMap { Success(block(it)) }

// Monad
fun <A, E, B> Result<A, E>.flatMap(block: (A) -> Result<B, E>): Result<B, E> = when (this) {
    is Failure -> this
    is Success -> block(value)
}

// Fold operation: two lambdas for two result types
fun <A, E> Result<A, E>.fold(onSuccess: (A) -> Unit, onError: (E) -> Unit) = when (this) {
    is Failure -> onError(error)
    is Success -> onSuccess(value)
}