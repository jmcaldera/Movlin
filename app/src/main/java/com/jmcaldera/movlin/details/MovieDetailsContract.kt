package com.jmcaldera.movlin.details

import com.jmcaldera.movlin.base.BasePresenter
import com.jmcaldera.movlin.base.BaseView
import com.jmcaldera.movlin.model.CastMemberViewModel
import com.jmcaldera.movlin.model.MovieDetailsViewModel

/**
 * Created by jmcaldera on 13-03-18.
 */
interface MovieDetailsContract {

    interface View : BaseView {
        fun showDetails(movie: MovieDetailsViewModel)
        fun showCast(castList: List<CastMemberViewModel>)
        fun showLoading()
        fun hideLoading()
        fun showUnauthorizedError()
        fun showNotFoundError()
        fun isActive(): Boolean
    }

    interface Presenter : BasePresenter<View> {
        suspend fun loadMovieDetails(id: Int)
    }
}