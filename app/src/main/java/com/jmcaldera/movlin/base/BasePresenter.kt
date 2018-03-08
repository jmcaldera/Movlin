package com.jmcaldera.movlin.base

/**
 * Created by jmcaldera on 26-02-18.
 */
interface BasePresenter<out T: BaseView> {

    val view: T

    fun start()

}