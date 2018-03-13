package com.jmcaldera.movlin.di.subcomponent.details

import com.jmcaldera.movlin.details.MovieDetailsFragment
import dagger.Subcomponent

/**
 * Created by jmcaldera on 13-03-18.
 */
@Subcomponent(modules = [DetailsModule::class])
interface DetailsComponent {

    fun injectTo(detailsFragment: MovieDetailsFragment)
}