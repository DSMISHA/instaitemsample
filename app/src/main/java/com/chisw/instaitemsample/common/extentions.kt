package com.chisw.instaitemsample.common

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions



fun ImageView.loadImage(url: String?){
    Glide.with(this.context)
        .load(url)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}


fun ImageView.loadCircleImage(url: String?){
    Glide.with(this.context)
        .load(url)
        .transition(DrawableTransitionOptions.withCrossFade())
        .optionalCircleCrop()
        .into(this)
}

fun View.visibleIf(predicate: () -> Boolean) {
    when(predicate.invoke()){
        true -> visible()
        else -> gone()
    }
}

fun View.visible(){
    this.visibility = View.VISIBLE
}

fun View.gone(){
    this.visibility = View.GONE
}