package com.gk.android.ui_components

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(url: String?) {
    if (url.isNullOrBlank()) return
    Glide.with(this).load(url).placeholder(R.drawable.ic_baseline_image_24).into(this)
}
