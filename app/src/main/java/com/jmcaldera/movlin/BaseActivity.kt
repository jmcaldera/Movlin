package com.jmcaldera.movlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.IdRes
import com.jmcaldera.movlin.extension.inTransaction

/**
 * BaseActivity. Every new activity must extend from this
 * Based on Fernando Cejas Base class
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addFragment(savedInstanceState)
    }

    override fun onBackPressed() {
        (supportFragmentManager.findFragmentById(fragmentContainer()) as BaseFragment).onBackPressed()
        super.onBackPressed()
    }

    private fun addFragment(savedInstanceState: Bundle?) =
            savedInstanceState ?: supportFragmentManager.inTransaction { add(fragmentContainer(), fragment()) }

    @IdRes abstract fun fragmentContainer() : Int

    abstract fun fragment(): BaseFragment

}
