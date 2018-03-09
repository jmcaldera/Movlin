package com.jmcaldera.movlin.movies.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.jmcaldera.data.remote.TmdbEndpoints
import com.jmcaldera.movlin.R
import com.jmcaldera.movlin.extension.inflate
import com.jmcaldera.movlin.extension.loadFromUrl
import com.jmcaldera.movlin.model.MovieViewModel
import kotlinx.android.synthetic.main.item_movie_carousel.view.*
import kotlin.properties.Delegates

/**
 * Created by jmcaldera on 08-03-18.
 */
class CarouselAdapter() : RecyclerView.Adapter<CarouselAdapter.ViewHolder>() {

    internal var movieList: List<MovieViewModel> by Delegates.observable(emptyList()) {
        _, _, _ -> notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(parent?.inflate(R.layout.item_movie_carousel)!!)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bind(movieList[position])
    }

    override fun getItemCount(): Int = movieList.size


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(movie: MovieViewModel) = with(movie) {
            itemView.image_poster.loadFromUrl(TmdbEndpoints.POSTER_URL_W185 + posterPath)
        }
    }
}