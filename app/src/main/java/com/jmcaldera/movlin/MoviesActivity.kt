package com.jmcaldera.movlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.jmcaldera.domain.functional.asyncAwait
import com.jmcaldera.domain.functional.fold
import com.jmcaldera.domain.usecase.*
import com.jmcaldera.movlin.di.ApplicationComponent
import com.jmcaldera.movlin.model.MovieViewModel
import kotlinx.android.synthetic.main.activity_movies.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import org.jetbrains.anko.toast
import javax.inject.Inject

class MoviesActivity : AppCompatActivity() {

    @Inject
    lateinit var getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase

    @Inject
    lateinit var getPopularMoviesUseCase: GetPopularMoviesUseCase

    @Inject
    lateinit var getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase

    @Inject
    lateinit var getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)
        injectDependencies(App.appComponent)

        list_now_playing.layoutManager = LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false)
        list_now_playing.addItemDecoration(DividerItemDecoration(list_now_playing.context,
                DividerItemDecoration.HORIZONTAL))
        list_now_playing.adapter = CarouselAdapter()

        list_top_rated.layoutManager = LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false)
        list_top_rated.addItemDecoration(DividerItemDecoration(list_top_rated.context,
                DividerItemDecoration.HORIZONTAL))
        list_top_rated.adapter = CarouselAdapter()

        list_popular.layoutManager = LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false)
        list_popular.addItemDecoration(DividerItemDecoration(list_popular.context,
                DividerItemDecoration.HORIZONTAL))
        list_popular.adapter = CarouselAdapter()

        list_upcoming.layoutManager = LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false)
        list_upcoming.addItemDecoration(DividerItemDecoration(list_upcoming.context,
                DividerItemDecoration.HORIZONTAL))
        list_upcoming.adapter = CarouselAdapter()
    }

    private fun injectDependencies(appComponent: ApplicationComponent) {
        appComponent.inject(this)
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
                        onError = { toast("Not Found!") }
                )
            }

            getTopRatedMoviesUseCase.execute().asyncAwait {
                it.fold(
                        onSuccess = {
                            (list_top_rated.adapter as CarouselAdapter).movieList =
                                    it.movies.map { MovieViewModel(it.title, it.posterPath) }
                        },
                        onError = { toast("Not Found!") }
                )
            }

            getPopularMoviesUseCase.execute().asyncAwait {
                it.fold(
                        onSuccess = {
                            (list_popular.adapter as CarouselAdapter).movieList =
                                    it.movies.map { MovieViewModel(it.title, it.posterPath) }
                        },
                        onError = { toast("Not Found!") }
                )
            }

            getUpcomingMoviesUseCase.execute().asyncAwait {
                it.fold(
                        onSuccess = {
                            (list_upcoming.adapter as CarouselAdapter).movieList =
                                    it.movies.map { MovieViewModel(it.title, it.posterPath) }
                        },
                        onError = { toast("Not Found!") }
                )
            }
        }
    }
}
