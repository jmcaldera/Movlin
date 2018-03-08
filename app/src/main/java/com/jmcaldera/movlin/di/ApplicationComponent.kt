package com.jmcaldera.movlin.di

import com.jmcaldera.movlin.MoviesActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by jmcaldera on 21-02-18.
 */
@Singleton
@Component(modules = [ApplicationModule::class, DataModule::class, RepositoryModule::class, DomainModule::class])
interface ApplicationComponent {

    fun inject(activity: MoviesActivity)
}