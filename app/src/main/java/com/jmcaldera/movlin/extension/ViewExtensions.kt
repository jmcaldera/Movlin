package com.jmcaldera.movlin.extension

import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso

/**
 * Created by jmcaldera on 08-03-18.
 */
inline fun ViewGroup.inflate(@LayoutRes layoutRes: Int): View =
    LayoutInflater.from(context).inflate(layoutRes, this, false)

inline fun ImageView.loadFromUrl(url: String) = Picasso.with(context).load(url).into(this)