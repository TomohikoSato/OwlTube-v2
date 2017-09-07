package com.sgr.owltube_v2.frontend.common

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide

@BindingAdapter("bind:imageUrl")
fun setImageUrl(imageView: ImageView, imageUrl: String) {
    Glide.with(imageView.context)
            .load(imageUrl)
            .into(imageView);
}