package com.jmcaldera.movlin.di

import android.content.Context
import com.jmcaldera.movlin.App
import com.jmcaldera.movlin.di.qualifier.ApplicationQualifier
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by jmcaldera on 21-02-18.
 */
@Module
class ApplicationModule(private val app: App) {

    @Provides
    @Singleton
    fun provideApplication(): App = app

    @Provides
    @Singleton
    @ApplicationQualifier
    fun provideApplicationContext(): Context = app
}