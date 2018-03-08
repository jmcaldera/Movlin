package com.jmcaldera.movlin.di

import com.jmcaldera.domain.repository.MoviesRepository
import com.jmcaldera.domain.usecase.GetNowPlayingMoviesUseCase
import com.jmcaldera.domain.usecase.GetPopularMoviesUseCase
import com.jmcaldera.domain.usecase.GetTopRatedMoviesUseCase
import com.jmcaldera.domain.usecase.GetUpcomingMoviesUseCase
import dagger.Module
import dagger.Provides

/**
 * Created by jmcaldera on 08-03-18.
 */
@Module
class DomainModule {

    @Provides
    fun provideGetNowPlayingMoviesUseCase(moviesRepository: MoviesRepository): GetNowPlayingMoviesUseCase =
            GetNowPlayingMoviesUseCase(moviesRepository)

    @Provides
    fun provideGetPopularMoviesUseCase(moviesRepository: MoviesRepository): GetPopularMoviesUseCase =
            GetPopularMoviesUseCase(moviesRepository)

    @Provides
    fun provideGetTopRatedMoviesUseCase(moviesRepository: MoviesRepository): GetTopRatedMoviesUseCase =
            GetTopRatedMoviesUseCase(moviesRepository)

    @Provides
    fun provideUpcomingMoviesUseCase(moviesRepository: MoviesRepository): GetUpcomingMoviesUseCase =
            GetUpcomingMoviesUseCase(moviesRepository)
}