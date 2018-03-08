package com.jmcaldera.domain.usecase

/**
 * Created by jmcaldera on 21-02-18.
 */
abstract class UseCase<out T, in Params> {

    abstract suspend fun execute(params: Params) : T

    class None
}