package com.jmcaldera.movlin.di.subcomponent.movies

import com.jmcaldera.domain.usecase.GetNowPlayingMoviesUseCase
import com.jmcaldera.domain.usecase.GetPopularMoviesUseCase
import com.jmcaldera.domain.usecase.GetTopRatedMoviesUseCase
import com.jmcaldera.domain.usecase.GetUpcomingMoviesUseCase
import com.jmcaldera.movlin.movies.MoviesContract
import com.jmcaldera.movlin.movies.MoviesPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by jmcaldera on 13-03-18.
 */
@Module
class MoviesModule {

    @Provides
    fun provideMoviesPresenter(getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
                               getPopularMoviesUseCase: GetPopularMoviesUseCase,
                               getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
                               getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase) : MoviesContract.Presenter =
            MoviesPresenter(getNowPlayingMoviesUseCase, getPopularMoviesUseCase,
                    getTopRatedMoviesUseCase, getUpcomingMoviesUseCase)
}