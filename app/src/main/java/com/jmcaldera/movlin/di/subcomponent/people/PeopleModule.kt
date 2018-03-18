package com.jmcaldera.movlin.di.subcomponent.people

import com.jmcaldera.domain.usecase.GetPersonDetailsUseCase
import com.jmcaldera.movlin.people.PeopleContract
import com.jmcaldera.movlin.people.PeoplePresenter
import dagger.Module
import dagger.Provides

/**
 * Created by jmcaldera on 18-03-18.
 */
@Module
class PeopleModule {

    @Provides
    fun providePeoplePresenter(getPersonDetailsUseCase: GetPersonDetailsUseCase):
            PeopleContract.Presenter = PeoplePresenter(getPersonDetailsUseCase)
}