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

    private lateinit var movieList: List<Movie>

    private lateinit var movieViewModel: MovieViewModel

    private lateinit var presenter: MoviesPresenter

    @Before
    fun setUp() = runBlocking {

        movieViewModel = MovieViewModel(1, "title", "/poster", "date",
                1.0, 1)

        movieList = listOf(Movie(1, 1, false, 1.0, "title",
                1.0, "/poster", "en", "title", false,
                "over", "date", "/path", null))

        `when`(getNowPlayingMoviesUseCase.execute()).thenReturn(movieList.result())
        `when`(getUpcomingMoviesUseCase.execute()).thenReturn(movieList.result())
        `when`(getPopularMoviesUseCase.execute()).thenReturn(movieList.result())
        `when`(getTopRatedMoviesUseCase.execute()).thenReturn(movieList.result())

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

        verify(view).showNowPlayingMovies(movieList.map { MovieViewModel(it.id, it.title, it.posterPath,
                it.releaseDate, it.voteAverage, it.voteCount) })
        verify(view).showUpcomingMovies(movieList.map { MovieViewModel(it.id, it.title, it.posterPath,
                it.releaseDate, it.voteAverage, it.voteCount) })
        verify(view).showPopularMovies(movieList.map { MovieViewModel(it.id, it.title, it.posterPath,
                it.releaseDate, it.voteAverage, it.voteCount) })
        verify(view).showTopRatedMovies(movieList.map { MovieViewModel(it.id, it.title, it.posterPath,
                it.releaseDate, it.voteAverage, it.voteCount) })

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