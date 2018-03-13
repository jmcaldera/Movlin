package com.jmcaldera.movlin.base

/**
 * Created by jmcaldera on 26-02-18.
 */
interface BasePresenter<T: BaseView> {

    var view: T

    suspend fun start()

}