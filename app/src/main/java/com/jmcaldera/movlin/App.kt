package com.jmcaldera.movlin

import android.app.Application
import com.jmcaldera.movlin.di.ApplicationComponent
import com.jmcaldera.movlin.di.ApplicationModule
import com.jmcaldera.movlin.di.DaggerApplicationComponent
import com.squareup.leakcanary.LeakCanary

/**
 * Created by jmcaldera on 21-02-18.
 */
class App: Application() {

    companion object {
        lateinit var appComponent: ApplicationComponent
    }
    override fun onCreate() {
        super.onCreate()
        initializeDagger()
        initializeLeakCanary()
    }

    private fun initializeDagger() {
        appComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    private fun initializeLeakCanary() {
        if (BuildConfig.DEBUG) LeakCanary.install(this)
    }
}