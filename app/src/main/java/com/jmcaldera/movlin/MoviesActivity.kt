package com.jmcaldera.movlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.jmcaldera.domain.functional.asyncAwait
import com.jmcaldera.domain.functional.fold
import com.jmcaldera.domain.usecase.GetTopRatedMoviesUseCase
import com.jmcaldera.domain.usecase.UseCase
import com.jmcaldera.movlin.di.ApplicationComponent
import com.jmcaldera.movlin.model.MovieViewModel
import kotlinx.android.synthetic.main.activity_movies.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import org.jetbrains.anko.toast
import javax.inject.Inject

class MoviesActivity : AppCompatActivity() {

    @Inject
    lateinit var getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)
        injectDependencies(App.appComponent)

        list_now_playing.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        list_now_playing.adapter = CarouselAdapter()
    }

    private fun injectDependencies(appComponent: ApplicationComponent) {
        appComponent.inject(this)
    }

    override fun onResume() {
        super.onResume()
        launch(UI) {
            getTopRatedMoviesUseCase.execute().asyncAwait {
                it.fold(
                        onSuccess = { (list_now_playing.adapter as CarouselAdapter).movieList =
                                it.movies.map { MovieViewModel(it.title) }},
                        onError = { toast("Not Found!") }
                )
            }
        }
    }
}
