package com.chisw.instaitemsample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chisw.instaitemsample.R
import com.chisw.instaitemsample.common.loadImage
import kotlinx.android.synthetic.main.item_photo.view.*


class ImagesRecyclerAdapter : RecyclerView.Adapter<ImagesRecyclerAdapter.ViewHolder>() {

    private var data: List<String>? = null

    fun setData(items: List<String>?) {
        data = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = data?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        data?.get(position)?.let { holder.bind(it) }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(url: String) {
            itemView.ivPhoto.loadImage(url)
        }
    }
}