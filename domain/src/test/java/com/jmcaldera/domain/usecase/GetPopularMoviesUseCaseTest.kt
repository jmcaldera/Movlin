package com.jmcaldera.domain.usecase

import com.jmcaldera.domain.functional.Success
import com.jmcaldera.domain.functional.result
import com.jmcaldera.domain.model.TopRatedPopular
import com.jmcaldera.domain.repository.MoviesRepository
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by jmcaldera on 09-03-18.
 */
@RunWith(MockitoJUnitRunner::class)
class GetPopularMoviesUseCaseTest {

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    private lateinit var popularMovies: TopRatedPopular

    private lateinit var getPopularMoviesUseCase: GetPopularMoviesUseCase

    @Before
    fun setUp() = runBlocking {

        popularMovies = TopRatedPopular(1, 100, 20, emptyList())

        `when`(moviesRepository.getPopularMovies()).thenReturn(popularMovies.result())

        getPopularMoviesUseCase = GetPopularMoviesUseCase(moviesRepository)
    }

    @Test
    fun getPopularMovies_returnsSuccessResult() {
        val result = runBlocking { getPopularMoviesUseCase.execute().deferred.await() }
        val movies = (result as? Success)?.value
        assertEquals(movies, popularMovies)
    }
}