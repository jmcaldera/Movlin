package com.jmcaldera.movlin.people

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import com.jmcaldera.movlin.BaseActivity
import com.jmcaldera.movlin.BaseFragment
import com.jmcaldera.movlin.R
import com.jmcaldera.movlin.extension.consume

class PeopleActivity : BaseActivity() {

    companion object {
        private const val ARG_PEOPLE_ID = "com.jmcaldera.ARG_PEOPLE_ID"

        fun newIntent(context: Context, id: Int): Intent {
            val intent = Intent(context, PeopleActivity::class.java)
            intent.putExtra(ARG_PEOPLE_ID, id)
            return intent
        }
    }

    override fun fragmentContainer(): Int = R.id.fragment_container

    override fun fragment(): BaseFragment =
            PeopleFragment.newInstance(intent.getIntExtra(ARG_PEOPLE_ID, 0))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_people)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when (item?.itemId) {
        android.R.id.home -> consume { onBackPressed() }
        else -> super.onOptionsItemSelected(item)
    }
}
