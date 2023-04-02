package com.example.lloydstask.ui.wrapper

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.lloydstask.R

class GlideImageLoader(
    private val context: Context
) : ImageLoader {
    override fun loadImage(
        imageUrl: String,
        placeholderId: Int,
        imageView: ImageView
    ) {
        Glide.with(context)
            .load(imageUrl)
            .placeholder(placeholderId)
            .fallback(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(imageView)
    }
}