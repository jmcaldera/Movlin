package com.jmcaldera.movlin.movies


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.jmcaldera.domain.functional.asyncAwait
import com.jmcaldera.domain.functional.fold
import com.jmcaldera.domain.usecase.GetNowPlayingMoviesUseCase
import com.jmcaldera.domain.usecase.GetPopularMoviesUseCase
import com.jmcaldera.domain.usecase.GetTopRatedMoviesUseCase
import com.jmcaldera.domain.usecase.GetUpcomingMoviesUseCase
import com.jmcaldera.movlin.BaseFragment
import com.jmcaldera.movlin.movies.adapter.CarouselAdapter

import com.jmcaldera.movlin.R
import com.jmcaldera.movlin.di.ApplicationComponent
import com.jmcaldera.movlin.model.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movies.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import org.jetbrains.anko.toast
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class MoviesFragment : BaseFragment() {

    @Inject
    lateinit var getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase

    @Inject
    lateinit var getPopularMoviesUseCase: GetPopularMoviesUseCase

    @Inject
    lateinit var getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase

    @Inject
    lateinit var getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase

    override fun fragmentId(): Int = R.layout.fragment_movies

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list_now_playing.layoutManager = LinearLayoutManager(context,
                LinearLayoutManager.HORIZONTAL, false)
        list_now_playing.adapter = CarouselAdapter()

        list_top_rated.layoutManager = LinearLayoutManager(context,
                LinearLayoutManager.HORIZONTAL, false)
        list_top_rated.adapter = CarouselAdapter()

        list_popular.layoutManager = LinearLayoutManager(context,
                LinearLayoutManager.HORIZONTAL, false)
        list_popular.adapter = CarouselAdapter()

        list_upcoming.layoutManager = LinearLayoutManager(context,
                LinearLayoutManager.HORIZONTAL, false)
        list_upcoming.adapter = CarouselAdapter()

    }

    override fun onResume() {
        super.onResume()
        launch(UI) {
            getNowPlayingMoviesUseCase.execute().asyncAwait {
                it.fold(
                        onSuccess = {
                            (list_now_playing.adapter as CarouselAdapter).movieList =
                                    it.movies.map { MovieViewModel(it.title, it.posterPath) }
                        },
                        onError = { activity?.toast("Not Found!") }
                )
            }

            getTopRatedMoviesUseCase.execute().asyncAwait {
                it.fold(
                        onSuccess = {
                            (list_top_rated.adapter as CarouselAdapter).movieList =
                                    it.movies.map { MovieViewModel(it.title, it.posterPath) }
                        },
                        onError = { activity?.toast("Not Found!") }
                )
            }

            getPopularMoviesUseCase.execute().asyncAwait {
                it.fold(
                        onSuccess = {
                            (list_popular.adapter as CarouselAdapter).movieList =
                                    it.movies.map { MovieViewModel(it.title, it.posterPath) }
                        },
                        onError = { activity?.toast("Not Found!") }
                )
            }

            getUpcomingMoviesUseCase.execute().asyncAwait {
                it.fold(
                        onSuccess = {
                            (list_upcoming.adapter as CarouselAdapter).movieList =
                                    it.movies.map { MovieViewModel(it.title, it.posterPath) }
                        },
                        onError = { activity?.toast("Not Found!") }
                )
            }
        }
    }

    override fun injectDependencies(appComponent: ApplicationComponent) {
        appComponent.inject(this)
    }

}// Required empty public constructor
