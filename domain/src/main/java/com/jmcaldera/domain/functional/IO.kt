package com.jmcaldera.domain.functional

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async

/**
 * Created by jmcaldera on 07-03-18.
 */
class IO<out A>(val deferred: Deferred<A>) {
    companion object
}

fun <A> IO.Companion.pure(a: A): IO<A> = IO(async(CommonPool) { a })

fun <A> IO.Companion.async(block: () -> A): IO<A> = IO(async(CommonPool) { block() })

suspend fun <A> IO<A>.asyncAwait(block: (A) -> Unit) {
    block(deferred.await())
}

fun <A, B> IO<A>.map(block: (A) -> B): IO<B> = flatMap { IO(async(CommonPool) { block(it) }) }

fun <A, B> IO<A>.flatMap(block: (A) -> IO<B>): IO<B> =
        IO(async { block(this@flatMap.deferred.await()).deferred.await() })
