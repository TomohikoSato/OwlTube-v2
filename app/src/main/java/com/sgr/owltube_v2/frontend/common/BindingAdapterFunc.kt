package com.sgr.owltube_v2.frontend.common

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.request.RequestOptions.circleCropTransform

@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, imageUrl: String) {
    GlideApp.with(imageView.context)
            .load(imageUrl)
            .into(imageView);
}

@BindingAdapter("circleCropImageUrl")
fun setCircleCropImageUrl(imageView: ImageView, imageUrl: String) {
    GlideApp.with(imageView.context)
            .load(imageUrl)
            .apply(circleCropTransform())
            .into(imageView);
}