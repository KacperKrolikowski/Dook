package com.krolikowski.dook.extensions

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

fun ImageView.loadFromUrl(
    url: String?,
    diskCacheStrategy: DiskCacheStrategy = DiskCacheStrategy.AUTOMATIC,
    doOnError: (() -> Unit)? = null,
    doOnSuccess: (() -> Unit)? = null
) {
    val glideRequest = Glide.with(context)
        .load(url)
        .diskCacheStrategy(diskCacheStrategy)
        .listener(provideGlideRequestListener(doOnError, doOnSuccess))

    glideRequest.into(this)
}

private fun provideGlideRequestListener(
    doOnError: (() -> Unit)? = null,
    doOnSuccess: (() -> Unit)? = null
): RequestListener<Drawable> {
    return object : RequestListener<Drawable> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<Drawable>?,
            isFirstResource: Boolean
        ): Boolean {
            doOnError?.invoke()
            return false
        }

        override fun onResourceReady(
            resource: Drawable?,
            model: Any?,
            target: Target<Drawable>?,
            dataSource: DataSource?,
            isFirstResource: Boolean
        ): Boolean {
            doOnSuccess?.invoke()
            return false
        }
    }
}