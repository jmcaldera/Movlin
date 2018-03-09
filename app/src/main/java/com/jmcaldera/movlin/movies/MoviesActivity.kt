package com.jmcaldera.movlin.movies

import android.os.Bundle
import com.jmcaldera.movlin.BaseActivity
import com.jmcaldera.movlin.BaseFragment
import com.jmcaldera.movlin.R

class MoviesActivity : BaseActivity() {

    override fun fragmentContainer(): Int = R.id.fragment_container

    override fun fragment(): BaseFragment = MoviesFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)
    }
}
