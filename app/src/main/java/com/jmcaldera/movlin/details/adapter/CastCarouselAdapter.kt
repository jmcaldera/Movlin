package com.jmcaldera.movlin.details.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.jmcaldera.data.remote.TmdbEndpoints
import com.jmcaldera.movlin.R
import com.jmcaldera.movlin.extension.inflate
import com.jmcaldera.movlin.extension.loadFromUrl
import com.jmcaldera.movlin.model.CastMemberViewModel
import kotlinx.android.synthetic.main.item_cast_carousel.view.*
import kotlin.properties.Delegates

/**
 * Created by jmcaldera on 15-03-18.
 */
class CastCarouselAdapter(private val itemClick: (CastMemberViewModel) -> Unit) :
        RecyclerView.Adapter<CastCarouselAdapter.ViewHolder>() {

    internal var castList: List<CastMemberViewModel> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(parent?.inflate(R.layout.item_cast_carousel)!!, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bind(castList[position])
    }

    override fun getItemCount(): Int = castList.size

    class ViewHolder(itemView: View, private val itemClick: (CastMemberViewModel) -> Unit) :
            RecyclerView.ViewHolder(itemView) {

        fun bind(castMember: CastMemberViewModel) = with(castMember) {
            itemView.imageProfile.loadFromUrl(TmdbEndpoints.PROFILE_URL_W185 + profilePath)
            itemView.nameProfile.text = name
            itemView.nameCharacter.text = character
            itemView.setOnClickListener { itemClick(this) }
        }

    }
}