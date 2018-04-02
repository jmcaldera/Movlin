package com.jmcaldera.movlin.details


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.graphics.drawable.DrawerArrowDrawable
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import androidx.view.isGone
import androidx.view.isVisible
import com.jmcaldera.data.remote.TmdbEndpoints
import com.jmcaldera.data.remote.TmdbEndpoints.Companion.BACKDROP_URL_W300
import com.jmcaldera.movlin.BaseFragment

import com.jmcaldera.movlin.R
import com.jmcaldera.movlin.details.adapter.CastCarouselAdapter
import com.jmcaldera.movlin.details.adapter.ImagesCarouselAdapter
import com.jmcaldera.movlin.di.ApplicationComponent
import com.jmcaldera.movlin.di.subcomponent.details.DetailsModule
import com.jmcaldera.movlin.extension.loadFromUrl
import com.jmcaldera.movlin.model.CastMemberViewModel
import com.jmcaldera.movlin.model.MovieDetailsViewModel
import com.jmcaldera.movlin.people.PeopleActivity
import kotlinx.android.synthetic.main.fragment_movie_details.*
import kotlinx.android.synthetic.main.layout_details_credits.*
import kotlinx.android.synthetic.main.layout_details_images.*
import kotlinx.android.synthetic.main.layout_details_summary.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import org.jetbrains.anko.toast
import java.text.SimpleDateFormat
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 * Use the [MovieDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MovieDetailsFragment : BaseFragment(), MovieDetailsContract.View {

    @Inject
    lateinit var presenter: MovieDetailsContract.Presenter

    override fun fragmentId(): Int = R.layout.fragment_movie_details

    private var movieId: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null) {
            movieId = arguments!!.getInt(ARG_MOVIE_ID, 0)
        }
        listImages.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        listImages.adapter = ImagesCarouselAdapter()

        listCast.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        listCast.adapter = CastCarouselAdapter { presenter.onCastMemberClicked(it) }

        toolbar.navigationIcon = DrawerArrowDrawable(toolbar.context).apply {
            progress = 1f
        }

        toolbar.setNavigationOnClickListener { activity?.onBackPressed() }

        presenter.view = this
    }

    override fun onResume() {
        super.onResume()
        launch(UI) {
            presenter.loadMovieDetails(movieId)
        }
    }

    override fun showDetails(movie: MovieDetailsViewModel) {

        with(movie) {
            collapsing_toolbar.title = title

            val sdf = SimpleDateFormat("yyyy-MM-dd")
            val date = sdf.parse(releaseDate)
            val outputFormat = SimpleDateFormat("yyyy")
            val year = outputFormat.format(date)
            movieDate.text = year

            movieRuntime.text = String.format("%2dh %02dmin", runtime / 60, runtime % 60)
            movieGenres.text = genres.map { it.name }.toString()

            moviePoster.loadFromUrl(TmdbEndpoints.POSTER_URL_W185 + posterPath)
            textVoteAverage.text = "$voteAverage/10"
            textVoteCount.text = voteCount.toString()
            movieShortSummary.text = overview

            if (backdropPath != null) {
                image_header.loadFromUrl(BACKDROP_URL_W300 + backdropPath)
            } else {
                image_header.isGone = true
            }

            (listImages.adapter as ImagesCarouselAdapter).imageList = images.backdrops
        }

    }

    override fun showCast(castList: List<CastMemberViewModel>) {
        (listCast.adapter as CastCarouselAdapter).castList = castList
    }

    override fun showLoading() {
        movieDetailsContainer.isGone = true
        movieImagesContainer.isGone = true
        movieCastContainer.isGone = true
        movieShortInfo.isGone = true
        progressBar.isVisible = true
        progressBar.show()
    }

    override fun hideLoading() {
        progressBar.hide()
        progressBar.isGone = true
        movieDetailsContainer.isVisible = true
        movieImagesContainer.isVisible = true
        movieCastContainer.isVisible = true
        movieShortInfo.isVisible = true
    }

    override fun showUnauthorizedError() {
        activity?.toast("Unauthorized!")
    }

    override fun showNotFoundError() {
        activity?.toast("Not Found!")
    }

    override fun navigateToPersonDetails(id: Int) {
        activity?.let {
            val intent = PeopleActivity.newIntent(it, id)
            startActivity(intent)
        }
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
