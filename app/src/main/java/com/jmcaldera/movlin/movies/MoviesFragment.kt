package com.jmcaldera.movlin.movies


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import androidx.view.isGone
import androidx.view.isVisible
import com.jmcaldera.domain.functional.asyncAwait
import com.jmcaldera.domain.functional.fold
import com.jmcaldera.domain.usecase.GetNowPlayingMoviesUseCase
import com.jmcaldera.domain.usecase.GetPopularMoviesUseCase
import com.jmcaldera.domain.usecase.GetTopRatedMoviesUseCase
import com.jmcaldera.domain.usecase.GetUpcomingMoviesUseCase
import com.jmcaldera.movlin.BaseFragment
import com.jmcaldera.movlin.movies.adapter.CarouselAdapter

import com.jmcaldera.movlin.R
import com.jmcaldera.movlin.details.MovieDetailsActivity
import com.jmcaldera.movlin.di.ApplicationComponent
import com.jmcaldera.movlin.di.subcomponent.movies.MoviesModule
import com.jmcaldera.movlin.model.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movies.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import org.jetbrains.anko.toast
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class MoviesFragment : BaseFragment(), MoviesContract.View {

    @Inject
    lateinit var presenter: MoviesContract.Presenter

    override fun fragmentId(): Int = R.layout.fragment_movies

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        presenter.view = this
    }

    private fun initViews() {
        list_now_playing.layoutManager = LinearLayoutManager(context,
                LinearLayoutManager.HORIZONTAL, false)
        list_now_playing.adapter = CarouselAdapter { presenter.onMovieClick(it) }

        list_top_rated.layoutManager = LinearLayoutManager(context,
                LinearLayoutManager.HORIZONTAL, false)
        list_top_rated.adapter = CarouselAdapter { presenter.onMovieClick(it) }

        list_popular.layoutManager = LinearLayoutManager(context,
                LinearLayoutManager.HORIZONTAL, false)
        list_popular.adapter = CarouselAdapter { presenter.onMovieClick(it) }

        list_upcoming.layoutManager = LinearLayoutManager(context,
                LinearLayoutManager.HORIZONTAL, false)
        list_upcoming.adapter = CarouselAdapter { presenter.onMovieClick(it) }

    }

    override fun onResume() {
        super.onResume()
        launch(UI) {
            presenter.start()
        }
    }

    override fun showLoading() {
        movies_container.isGone = true
        progress_bar.isVisible = true
        progress_bar.show()
    }

    override fun hideLoading() {
        progress_bar.hide()
        progress_bar.isGone = true
        movies_container.isVisible = true
    }

    override fun showUnauthorizedError() {
        activity?.toast("Unauthorized!")
    }

    override fun showNotFoundError() {
        activity?.toast("Not Found!")
    }

    override fun showNowPlayingMovies(movies: List<MovieViewModel>) {
        (list_now_playing.adapter as CarouselAdapter).movieList = movies
    }

    override fun showTopRatedMovies(movies: List<MovieViewModel>) {
        (list_top_rated.adapter as CarouselAdapter).movieList = movies
    }

    override fun showPopularMovies(movies: List<MovieViewModel>) {
        (list_popular.adapter as CarouselAdapter).movieList = movies
    }

    override fun showUpcomingMovies(movies: List<MovieViewModel>) {
        (list_upcoming.adapter as CarouselAdapter).movieList = movies
    }

    override fun navigateToMovieDetails(id: Int) {
        activity?.let {
            val intent = MovieDetailsActivity.newIntent(it, id)
            startActivity(intent)
        }
    }

    override fun isActive(): Boolean = isAdded

    override fun injectDependencies(appComponent: ApplicationComponent) {
        appComponent.plus(MoviesModule()).injectTo(this)
    }

}// Required empty public constructor
