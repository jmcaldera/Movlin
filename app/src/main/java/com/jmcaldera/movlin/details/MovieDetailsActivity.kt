package com.jmcaldera.movlin.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.jmcaldera.movlin.BaseActivity
import com.jmcaldera.movlin.BaseFragment
import com.jmcaldera.movlin.R

class MovieDetailsActivity : BaseActivity() {

    companion object {
        private const val ARG_MOVIE_ID = "com.jmcaldera.ARG_MOVIE_ID"

        fun newIntent(context: Context, id: Int) : Intent {
            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra(ARG_MOVIE_ID, id)
            return intent
        }
    }

    override fun fragmentContainer(): Int = R.id.fragment_container

    override fun fragment(): BaseFragment =
            MovieDetailsFragment.newInstance(intent.getIntExtra(ARG_MOVIE_ID, 0))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
    }
}
