package com.jmcaldera.movlin.extension

/**
 * Created by jmcaldera on 02-04-18.
 */
inline fun consume(f: () -> Unit) : Boolean {
    f()
    return true
}