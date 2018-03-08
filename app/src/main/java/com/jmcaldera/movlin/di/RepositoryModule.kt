package com.jmcaldera.movlin.di

import com.jmcaldera.data.MoviesRepositoryImpl
import com.jmcaldera.data.TmdbService
import com.jmcaldera.data.datasource.MoviesDataSource
import com.jmcaldera.data.remote.RemoteMoviesDataSource
import com.jmcaldera.domain.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by jmcaldera on 08-03-18.
 */
@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRemoteMoviesDataSource(service: TmdbService): MoviesDataSource =
            RemoteMoviesDataSource(service)

    @Provides
    @Singleton
    fun provideMoviesRepository(remoteDataSource: MoviesDataSource): MoviesRepository =
            MoviesRepositoryImpl(remoteDataSource)
}