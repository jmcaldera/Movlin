package com.jmcaldera.movlin.details

import com.jmcaldera.domain.functional.asError
import com.jmcaldera.domain.functional.result
import com.jmcaldera.domain.model.*
import com.jmcaldera.domain.usecase.GetMovieCreditsUseCase
import com.jmcaldera.domain.usecase.GetMovieDetailsUseCase
import com.jmcaldera.movlin.model.CastMemberViewModel
import com.jmcaldera.movlin.model.mapper.convertCreditsFromDomain
import com.jmcaldera.movlin.model.mapper.convertMovieDetailsFromDomain
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by jmcaldera on 04-04-18.
 */
@RunWith(MockitoJUnitRunner::class)
class MovieDetailsPresenterTest {

    companion object {
        const val ID = 1
    }

    @Mock
    private lateinit var getMovieDetailsUseCase: GetMovieDetailsUseCase

    @Mock
    private lateinit var getMovieCreditsUseCase: GetMovieCreditsUseCase

    @Mock
    private lateinit var view: MovieDetailsContract.View

    private lateinit var movieDetails: MovieDetails

    private lateinit var credits: Credits

    private lateinit var presenter: MovieDetailsPresenter

    @Before
    fun setUp() = runBlocking {

        movieDetails = MovieDetails(1, 1, 1, false, 5.0, "title",
                5.0, "/poster", "en", "title", emptyList(),
                "/backdrop", false, "overview", "release",
                100, 100, Images(emptyList(), emptyList()))

        credits = Credits(1, emptyList(), emptyList())

        `when`(getMovieDetailsUseCase.execute(GetMovieDetailsUseCase.Params(ID)))
                .thenReturn(movieDetails.result())

        `when`(getMovieCreditsUseCase.execute(GetMovieCreditsUseCase.Params(ID)))
                .thenReturn(credits.result())

        `when`(view.isActive()).thenReturn(true)

        presenter = MovieDetailsPresenter(getMovieDetailsUseCase, getMovieCreditsUseCase)
        presenter.view = view

    }

    @Test
    fun loadDetailsAndCredits_showsIntoView() = runBlocking {

        presenter.loadMovieDetails(ID)

        verify(getMovieDetailsUseCase).execute(GetMovieDetailsUseCase.Params(ID))
        verify(view).showDetails(convertMovieDetailsFromDomain(movieDetails))

        verify(getMovieCreditsUseCase).execute(GetMovieCreditsUseCase.Params(ID))
        verify(view).showCast(convertCreditsFromDomain(credits).cast)

    }

    @Test
    fun loadDetailsAndCredits_showUnauthorizedErrorInView() = runBlocking {
        `when`(getMovieDetailsUseCase.execute(GetMovieDetailsUseCase.Params(ID)))
                .thenReturn(UnauthorizedError().asError())

        `when`(getMovieCreditsUseCase.execute(GetMovieCreditsUseCase.Params(ID)))
                .thenReturn(UnauthorizedError().asError())

        presenter.loadMovieDetails(ID)

        verify(getMovieDetailsUseCase).execute(GetMovieDetailsUseCase.Params(ID))
        verify(getMovieCreditsUseCase).execute(GetMovieCreditsUseCase.Params(ID))

        verify(view, times(2)).showUnauthorizedError()
    }

    @Test
    fun loadDetailsAndCredits_showNotFoundErrorInView() = runBlocking {
        `when`(getMovieDetailsUseCase.execute(GetMovieDetailsUseCase.Params(ID)))
                .thenReturn(NotFoundError().asError())

        `when`(getMovieCreditsUseCase.execute(GetMovieCreditsUseCase.Params(ID)))
                .thenReturn(NotFoundError().asError())

        presenter.loadMovieDetails(ID)

        verify(getMovieDetailsUseCase).execute(GetMovieDetailsUseCase.Params(ID))
        verify(getMovieCreditsUseCase).execute(GetMovieCreditsUseCase.Params(ID))

        verify(view, times(2)).showNotFoundError()
    }

    @Test
    fun onCastMemberClick_navigateToPersonDetails() = runBlocking {
        presenter.onCastMemberClicked(CastMemberViewModel(ID, "name", "char", null))

        verify(view).isActive()
        verify(view).navigateToPersonDetails(ID)
    }
}