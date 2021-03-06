package com.jmcaldera.domain.usecase

import com.jmcaldera.domain.functional.Success
import com.jmcaldera.domain.functional.result
import com.jmcaldera.domain.model.Movie
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
class GetNowPlayingMoviesUseCaseTest {

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    private lateinit var movieList: List<Movie>

    private lateinit var getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase

    @Before
    fun setUp() = runBlocking {

        movieList = listOf(Movie(1, 1, false, 1.0, "title",
                1.0, "/poster", "en", "title", false,
                "over", "date", "/path", null))

        `when`(moviesRepository.getNowPlayingMovies()).thenReturn(movieList.result())

        getNowPlayingMoviesUseCase = GetNowPlayingMoviesUseCase(moviesRepository)
    }

    @Test
    fun getNowPlayingMovies_returnsSuccessResult() {
        val result = runBlocking { getNowPlayingMoviesUseCase.execute().deferred.await() }
        val movies = (result as? Success)?.value
        assertEquals(movies, movieList)
    }
}