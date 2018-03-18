package com.jmcaldera.movlin.di.subcomponent.people

import com.jmcaldera.movlin.people.PeopleFragment
import dagger.Subcomponent

/**
 * Created by jmcaldera on 18-03-18.
 */
@Subcomponent(modules = [PeopleModule::class])
interface PeopleComponent {

    fun injectTo(peopleFragment: PeopleFragment)
}