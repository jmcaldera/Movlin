package com.jmcaldera.movlin.people

import com.jmcaldera.movlin.base.BasePresenter
import com.jmcaldera.movlin.base.BaseView
import com.jmcaldera.movlin.model.PersonViewModel

/**
 * Created by jmcaldera on 18-03-18.
 */
interface PeopleContract {

    interface View : BaseView {
        fun showDetails(person: PersonViewModel)
        fun showLoading()
        fun hideLoading()
        fun showUnauthorizedError()
        fun showNotFoundError()
        fun isActive(): Boolean
    }

    interface Presenter : BasePresenter<View> {
        suspend fun loadPersonDetails(id: Int)
    }
}