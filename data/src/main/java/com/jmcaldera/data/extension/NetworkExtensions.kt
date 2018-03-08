package com.jmcaldera.data.extension

import com.jmcaldera.domain.functional.*
import retrofit2.Call

/**
 * Created by jmcaldera on 08-03-18.
 */
fun <A, B> Call<A>.transformResult(f: A.() -> B?): IOResult<B?, Nothing> =
        IO.async { Result.pure(execute().body()?.f()) }

fun <A, E> IOResult<A?, Nothing>.orElse(f: () -> E): IOResult<A, E> =
        bind { it?.result() ?: f().asError() }
