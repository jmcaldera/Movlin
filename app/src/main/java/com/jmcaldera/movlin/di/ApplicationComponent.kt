package com.jmcaldera.movlin.di

import com.jmcaldera.movlin.di.subcomponent.details.DetailsComponent
import com.jmcaldera.movlin.di.subcomponent.details.DetailsModule
import com.jmcaldera.movlin.di.subcomponent.movies.MoviesComponent
import com.jmcaldera.movlin.di.subcomponent.movies.MoviesModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by jmcaldera on 21-02-18.
 */
@Singleton
@Component(modules = [ApplicationModule::class, DataModule::class, RepositoryModule::class, DomainModule::class])
interface ApplicationComponent {

    fun plus(module: MoviesModule): MoviesComponent
    fun plus(module: DetailsModule): DetailsComponent
}