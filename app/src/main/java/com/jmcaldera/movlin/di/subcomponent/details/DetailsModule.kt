package com.jmcaldera.movlin.di.subcomponent.details

import com.jmcaldera.domain.usecase.GetMovieCreditsUseCase
import com.jmcaldera.domain.usecase.GetMovieDetailsUseCase
import com.jmcaldera.movlin.details.MovieDetailsContract
import com.jmcaldera.movlin.details.MovieDetailsPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by jmcaldera on 13-03-18.
 */
@Module
class DetailsModule {

    @Provides
    fun provideMovieDetailsPresenter(getMovieDetailsUseCase: GetMovieDetailsUseCase,
                                     getMovieCreditsUseCase: GetMovieCreditsUseCase):
            MovieDetailsContract.Presenter =
            MovieDetailsPresenter(getMovieDetailsUseCase, getMovieCreditsUseCase)
}