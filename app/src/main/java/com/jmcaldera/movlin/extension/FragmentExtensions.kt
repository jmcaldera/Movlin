package com.jmcaldera.movlin.extension

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction

/**
 * Created by jmcaldera on 08-03-18.
 */
inline fun FragmentManager.inTransaction(function: FragmentTransaction.() -> FragmentTransaction) =
        beginTransaction().function().commit()