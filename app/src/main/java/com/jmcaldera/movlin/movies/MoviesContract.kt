package com.jmcaldera.movlin.movies

import com.jmcaldera.movlin.base.BasePresenter
import com.jmcaldera.movlin.base.BaseView
import com.jmcaldera.movlin.model.MovieViewModel

/**
 * Created by jmcaldera on 13-03-18.
 */
interface MoviesContract {

    interface View : BaseView {
        fun showLoading(show: Boolean)
        fun showUnauthorizedError()
        fun showNotFoundError()
        fun showNowPlayingMovies(movies: List<MovieViewModel>)
        fun showTopRatedMovies(movies: List<MovieViewModel>)
        fun showPopularMovies(movies: List<MovieViewModel>)
        fun showUpcomingMovies(movies: List<MovieViewModel>)
        fun isActive(): Boolean
    }

    interface Presenter : BasePresenter<View> {

    }
}