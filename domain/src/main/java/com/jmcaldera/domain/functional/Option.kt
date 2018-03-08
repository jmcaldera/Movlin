package com.jmcaldera.domain.functional

/**
 * Created by jmcaldera on 06-03-18.
 */
sealed class Option<out A>

object None : Option<Nothing>()
data class Some<out A>(val value: A) : Option<A>()

// Functor: applies a function to a wrapped value (map)
inline fun <A, B> Option<A>.map(block: (A) -> B): Option<B> = flatMap { Some(block(it)) }

// Monad: applies a function that returns a wrapped value to a wrapped value (flatMap)
inline fun <A, B> Option<A>.flatMap(block: (A) -> Option<B>): Option<B> = when (this) {
    None -> None
    is Some -> block(value)
}