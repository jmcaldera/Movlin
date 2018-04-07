package com.jmcaldera.domain.usecase

import com.jmcaldera.domain.functional.Success
import com.jmcaldera.domain.functional.result
import com.jmcaldera.domain.model.NowPlayingUpcoming
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

    private lateinit var nowPlayingMovies: NowPlayingUpcoming

    private lateinit var getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase

    @Before
    fun setUp() = runBlocking {

        nowPlayingMovies = NowPlayingUpcoming(emptyList(), 1, 100,
                10)

        `when`(moviesRepository.getNowPlayingMovies()).thenReturn(nowPlayingMovies.result())

        getNowPlayingMoviesUseCase = GetNowPlayingMoviesUseCase(moviesRepository)
    }

    @Test
    fun getNowPlayingMovies_returnsSuccessResult() {
        val result = runBlocking { getNowPlayingMoviesUseCase.execute().deferred.await() }
        val movies = (result as? Success)?.value
        assertEquals(movies, nowPlayingMovies)
    }
}