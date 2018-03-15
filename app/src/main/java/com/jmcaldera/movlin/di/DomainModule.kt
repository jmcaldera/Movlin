package com.jmcaldera.movlin.di

import com.jmcaldera.domain.repository.MoviesRepository
import com.jmcaldera.domain.usecase.*
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

    @Provides
    fun provideGetMovieDetailsUseCase(moviesRepository: MoviesRepository): GetMovieDetailsUseCase =
            GetMovieDetailsUseCase(moviesRepository)

    @Provides
    fun provideGetMovieCreditsUseCase(moviesRepository: MoviesRepository) : GetMovieCreditsUseCase =
            GetMovieCreditsUseCase(moviesRepository)
}