package com.jmcaldera.movlin.details.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.jmcaldera.data.remote.TmdbEndpoints
import com.jmcaldera.movlin.R
import com.jmcaldera.movlin.extension.inflate
import com.jmcaldera.movlin.extension.loadFromUrl
import com.jmcaldera.movlin.model.ImageViewModel
import kotlinx.android.synthetic.main.item_image_carousel.view.*
import kotlin.properties.Delegates

/**
 * Created by jmcaldera on 15-03-18.
 */
class ImagesCarouselAdapter :
        RecyclerView.Adapter<ImagesCarouselAdapter.ViewHolder>() {

    internal var imageList: List<ImageViewModel> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(parent?.inflate(R.layout.item_image_carousel)!!)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bind(imageList[position])
    }

    override fun getItemCount(): Int = imageList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(movie: ImageViewModel) = with(movie) {
            itemView.image.loadFromUrl(TmdbEndpoints.BACKDROP_URL_W300 + filePath)
        }
    }

}