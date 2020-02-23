package com.example.sporttracker

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


var sharedOptions = RequestOptions()
    .fitCenter()
    .error(R.drawable.ic_medal)

fun ImageView.loadImage(url:String?){


    Glide
        .with(this.context)
        .load(url)
        .apply(sharedOptions)
        .into(this)

}
