package com.jmcaldera.movlin.details


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.view.isGone
import androidx.view.isVisible
import com.jmcaldera.movlin.BaseFragment

import com.jmcaldera.movlin.R
import com.jmcaldera.movlin.di.ApplicationComponent
import com.jmcaldera.movlin.di.subcomponent.details.DetailsModule
import com.jmcaldera.movlin.model.MovieDetailsViewModel
import kotlinx.android.synthetic.main.fragment_movie_details.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import org.jetbrains.anko.toast
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 * Use the [MovieDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MovieDetailsFragment : BaseFragment(), MovieDetailsContract.View {

    @Inject
    lateinit var presenter : MovieDetailsContract.Presenter

    override fun fragmentId(): Int = R.layout.fragment_movie_details

    private var movieId: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null) {
            movieId = arguments!!.getInt(ARG_MOVIE_ID, 0)
        }
        presenter.view = this
    }

    override fun onResume() {
        super.onResume()
        launch(UI) {
            presenter.loadMovieDetails(movieId)
        }
    }

    override fun showDetails(movie: MovieDetailsViewModel) {
        movieTitle.text = movie.title
    }

    override fun showLoading() {
        movieTitle.isGone = true
        progressBar.isVisible = true
        progressBar.show()
    }

    override fun hideLoading() {
        progressBar.hide()
        progressBar.isGone = true
        movieTitle.isVisible = true
    }

    override fun showUnauthorizedError() {
        activity?.toast("Unauthorized!")
    }

    override fun showNotFoundError() {
        activity?.toast("Not Found!")
    }

    override fun isActive(): Boolean = isAdded

    override fun injectDependencies(appComponent: ApplicationComponent) {
        appComponent.plus(DetailsModule()).injectTo(this)
    }

    companion object {

        private const val ARG_MOVIE_ID = "com.jmcaldera.ARG_MOVIE_ID"
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param id Movie Id.
         * @return A new instance of fragment MovieDetailsFragment.
         */
        fun newInstance(id: Int): MovieDetailsFragment {
            val fragment = MovieDetailsFragment()
            val args = Bundle()
            args.putInt(ARG_MOVIE_ID, id)
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
