package com.bwell.helper

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.bwell.R


fun loadImage(imageView: ImageView, url: String?) {
    val circularProgressDrawable = CircularProgressDrawable(imageView.context)
    circularProgressDrawable.apply {
        strokeWidth = 5f
        centerRadius = 30f
        start()
    }

    Glide.with(imageView).load(url)
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                circularProgressDrawable.setVisible(false, false)
                return false
            }

        }).centerCrop()
        .error(R.drawable.ic_launcher_background)
        .placeholder(circularProgressDrawable).into(imageView)

}
