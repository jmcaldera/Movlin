package com.jmcaldera.domain.functional

/**
 * Created by jmcaldera on 08-03-18.
 */
typealias IOResult<A, E> = IO<Result<A, E>>

fun <A> A.result(): IOResult<A, Nothing> = this.let(Result.Companion::pure).let(IO.Companion::pure)

fun <E> E.asError(): IOResult<Nothing, E> = IO.pure(Failure(this))

infix fun <A, E, B> IOResult<A, E>.bind(transform: (A) -> IOResult<B, E>): IOResult<B, E> =
        this.flatMap {
            when (it) {
                is Success -> transform(it.value)
                is Failure -> IO.pure(it)
            }
        }