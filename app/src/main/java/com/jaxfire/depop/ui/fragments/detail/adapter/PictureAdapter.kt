package com.jaxfire.depop.ui.fragments.detail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jaxfire.depop.R
import kotlinx.android.synthetic.main.product_picture_pager_item.view.*

class PictureAdapter(
    private val pictures: List<String>,
) :
    RecyclerView.Adapter<PictureAdapter.PictureHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureHolder =
        PictureHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.product_picture_pager_item, parent, false),
        )

    override fun onBindViewHolder(holder: PictureHolder, position: Int) {
        holder.bindPicture(pictures[position])
    }

    override fun getItemCount() = pictures.size

    class PictureHolder(
        private val view: View
    ) : RecyclerView.ViewHolder(view) {

        fun bindPicture(url: String) {
            Glide
                .with(view)
                .load(url)
                .into(view.pagerImageView)
        }
    }
}
