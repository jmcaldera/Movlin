package com.jmcaldera.movlin.di.subcomponent.movies

import com.jmcaldera.movlin.movies.MoviesFragment
import dagger.Subcomponent

/**
 * Created by jmcaldera on 13-03-18.
 */
@Subcomponent(modules = [MoviesModule::class])
interface MoviesComponent {

    fun injectTo(moviesFragment: MoviesFragment)
}