package com.jmcaldera.domain.model

/**
 * Created by jmcaldera on 07-03-18.
 */
sealed class MovieError

class UnauthorizedError : MovieError()
class NotFoundError : MovieError()