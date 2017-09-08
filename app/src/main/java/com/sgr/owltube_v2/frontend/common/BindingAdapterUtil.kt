package com.sgr.owltube_v2.frontend.common

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.circleCropTransform
import com.sgr.owltube_v2.R

@BindingAdapter("bind:imageUrl")
fun setImageUrl(imageView: ImageView, imageUrl: String) {
    GlideApp.with(imageView.context)
            .load(imageUrl)
            .into(imageView);
}

@BindingAdapter("bind:circleCropImageUrl")
fun setCircleCropImageUrl(imageView: ImageView, imageUrl: String) {
    GlideApp.with(imageView.context)
            .load(imageUrl)
            .apply(circleCropTransform())
            .into(imageView);
}