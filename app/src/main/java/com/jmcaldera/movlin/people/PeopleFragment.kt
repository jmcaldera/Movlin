package com.jmcaldera.movlin.people


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import androidx.view.isGone
import androidx.view.isVisible
import com.jmcaldera.data.remote.TmdbEndpoints.Companion.PROFILE_URL_W185
import com.jmcaldera.movlin.BaseFragment

import com.jmcaldera.movlin.R
import com.jmcaldera.movlin.di.ApplicationComponent
import com.jmcaldera.movlin.di.subcomponent.people.PeopleModule
import com.jmcaldera.movlin.extension.loadFromUrl
import com.jmcaldera.movlin.model.PersonViewModel
import kotlinx.android.synthetic.main.fragment_people.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import org.jetbrains.anko.toast
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class PeopleFragment : BaseFragment(), PeopleContract.View {

    @Inject
    lateinit var presenter: PeopleContract.Presenter

    override fun fragmentId(): Int = R.layout.fragment_people

    private var peopleId: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null) {
            peopleId = arguments!!.getInt(ARG_PEOPLE_ID, 0)
        }
        presenter.view = this
    }

    override fun onResume() {
        super.onResume()
        launch(UI) {
            presenter.loadPersonDetails(peopleId)
        }
    }

    override fun showDetails(person: PersonViewModel) {
        with(person) {
            profileImage.loadFromUrl(PROFILE_URL_W185 + profilePath)
            birthdayText.text = birthday
//            if (deathday != null) // TODO
            birthPlaceText.text = placeOfBirth
            biographyText.text = biography
        }
    }

    override fun showLoading() {
        peopleContainer.isGone = true
        progressBar.isVisible = true
        progressBar.show()
    }

    override fun hideLoading() {
        progressBar.hide()
        progressBar.isGone = true
        peopleContainer.isVisible = true
    }

    override fun showUnauthorizedError() {
        activity?.toast("Unauthorized!")
    }

    override fun showNotFoundError() {
        activity?.toast("Not Found!")
    }

    override fun isActive(): Boolean = isAdded

    override fun injectDependencies(appComponent: ApplicationComponent) {
        appComponent.plus(PeopleModule()).injectTo(this)
    }

    companion object {

        private const val ARG_PEOPLE_ID = "com.jmcaldera.ARG_PEOPLE_ID"
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param id Movie Id.
         * @return A new instance of fragment MovieDetailsFragment.
         */
        fun newInstance(id: Int): PeopleFragment {
            val fragment = PeopleFragment()
            val args = Bundle()
            args.putInt(ARG_PEOPLE_ID, id)
            fragment.arguments = args
            return fragment
        }
    }

}// Required empty public constructor
