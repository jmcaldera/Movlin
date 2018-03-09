package com.jmcaldera.movlin


import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jmcaldera.movlin.di.ApplicationComponent


/**
 * Created by jmcaldera on 09-03-18.
 */
abstract class BaseFragment : Fragment() {

    @LayoutRes
    abstract fun fragmentId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependencies(App.appComponent)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(fragmentId(), container, false)
    }

    open fun onBackPressed() {}

    abstract fun injectDependencies(appComponent: ApplicationComponent)

}
