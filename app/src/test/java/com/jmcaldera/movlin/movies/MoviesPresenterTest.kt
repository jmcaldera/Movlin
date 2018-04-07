package com.jmcaldera.movlin.movies

import com.jmcaldera.domain.functional.asError
import com.jmcaldera.domain.functional.result
import com.jmcaldera.domain.model.*
import com.jmcaldera.domain.usecase.GetNowPlayingMoviesUseCase
import com.jmcaldera.domain.usecase.GetPopularMoviesUseCase
import com.jmcaldera.domain.usecase.GetTopRatedMoviesUseCase
import com.jmcaldera.domain.usecase.GetUpcomingMoviesUseCase
import com.jmcaldera.movlin.model.MovieViewModel
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by jmcaldera on 04-04-18.
 */
@RunWith(MockitoJUnitRunner::class)
class MoviesPresenterTest {

    @Mock
    private lateinit var getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase

    @Mock
    private lateinit var getPopularMoviesUseCase: GetPopularMoviesUseCase

    @Mock
    private lateinit var getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase

    @Mock
    private lateinit var getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase

    @Mock
    private lateinit var view : MoviesContract.View

    private lateinit var nowPlayingUpcoming: NowPlayingUpcoming

    private lateinit var topRatedPopular: TopRatedPopular

    private lateinit var movieViewModel: MovieViewModel

    private lateinit var presenter: MoviesPresenter

    @Before
    fun setUp() = runBlocking {

        nowPlayingUpcoming = NowPlayingUpcoming(emptyList(), 1, 1,
                1)

        topRatedPopular = TopRatedPopular(1, 1, 1, emptyList())

        movieViewModel = MovieViewModel(1, "title", "/poster")

        `when`(getNowPlayingMoviesUseCase.execute()).thenReturn(nowPlayingUpcoming.result())
        `when`(getUpcomingMoviesUseCase.execute()).thenReturn(nowPlayingUpcoming.result())
        `when`(getPopularMoviesUseCase.execute()).thenReturn(topRatedPopular.result())
        `when`(getTopRatedMoviesUseCase.execute()).thenReturn(topRatedPopular.result())

        `when`(view.isActive()).thenReturn(true)

        presenter = MoviesPresenter(getNowPlayingMoviesUseCase, getPopularMoviesUseCase,
                getTopRatedMoviesUseCase, getUpcomingMoviesUseCase)

        presenter.view = view
    }

    @Test
    fun loadMovieList_showIntoView() = runBlocking {
        presenter.start()

        verify(getNowPlayingMoviesUseCase).execute()
        verify(getUpcomingMoviesUseCase).execute()
        verify(getPopularMoviesUseCase).execute()
        verify(getTopRatedMoviesUseCase).execute()

        verify(view).showNowPlayingMovies(nowPlayingUpcoming.movies.map { MovieViewModel(it.id, it.title, it.posterPath) })
        verify(view).showUpcomingMovies(nowPlayingUpcoming.movies.map { MovieViewModel(it.id, it.title, it.posterPath) })
        verify(view).showPopularMovies(nowPlayingUpcoming.movies.map { MovieViewModel(it.id, it.title, it.posterPath) })
        verify(view).showTopRatedMovies(nowPlayingUpcoming.movies.map { MovieViewModel(it.id, it.title, it.posterPath) })

    }

    @Test
    fun loadMoviesList_showUnauthorizedErrorInView() = runBlocking {

        `when`(getNowPlayingMoviesUseCase.execute()).thenReturn(UnauthorizedError().asError())
        `when`(getUpcomingMoviesUseCase.execute()).thenReturn(UnauthorizedError().asError())
        `when`(getPopularMoviesUseCase.execute()).thenReturn(UnauthorizedError().asError())
        `when`(getTopRatedMoviesUseCase.execute()).thenReturn(UnauthorizedError().asError())

        presenter.start()

        verify(getNowPlayingMoviesUseCase).execute()
        verify(getUpcomingMoviesUseCase).execute()
        verify(getPopularMoviesUseCase).execute()
        verify(getTopRatedMoviesUseCase).execute()

        verify(view, times(4)).showUnauthorizedError()
    }

    @Test
    fun loadMoviesList_showNotFoundErrorInView() = runBlocking {

        `when`(getNowPlayingMoviesUseCase.execute()).thenReturn(NotFoundError().asError())
        `when`(getUpcomingMoviesUseCase.execute()).thenReturn(NotFoundError().asError())
        `when`(getPopularMoviesUseCase.execute()).thenReturn(NotFoundError().asError())
        `when`(getTopRatedMoviesUseCase.execute()).thenReturn(NotFoundError().asError())

        presenter.start()

        verify(getNowPlayingMoviesUseCase).execute()
        verify(getUpcomingMoviesUseCase).execute()
        verify(getPopularMoviesUseCase).execute()
        verify(getTopRatedMoviesUseCase).execute()

        verify(view, times(4)).showNotFoundError()
    }

    @Test
    fun onMovieClick_navigateToDetails() = runBlocking {
        presenter.onMovieClick(movieViewModel)

        verify(view).navigateToMovieDetails(movieViewModel.id)
    }
}