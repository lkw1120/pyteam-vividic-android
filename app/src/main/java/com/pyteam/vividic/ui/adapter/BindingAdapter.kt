package com.pyteam.vividic.ui.adapter

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.pyteam.vividic.R
import jp.wasabeef.glide.transformations.BlurTransformation

@BindingAdapter("app:posterPath")
fun bindingPoster(view: ImageView, url: String?) {
    Glide.with(view.context)
        .load(view.context.getString(R.string.tmdb_image_url_original)+url)
        .placeholder(R.drawable.ic_image_24px)
        //.error(R.drawable.ic_broken_image_24px)
        .centerCrop()
        .into(view)
}
@BindingAdapter("app:backdropPath")
fun bindingBackdrop(view: ImageView, url: String?) {
    val transform = MultiTransformation<Bitmap>(BlurTransformation(25,3),CenterCrop())
    Glide.with(view.context)
        .load(view.context.getString(R.string.tmdb_image_url_original)+url)
        .apply(RequestOptions.bitmapTransform(transform))
        .into(view)
}
@BindingAdapter("app:stillPath")
fun bindingStill(view: ImageView, url: String?) {
    Glide.with(view.context)
        .load(view.context.getString(R.string.tmdb_image_url_original)+url)
        .placeholder(R.drawable.ic_image_24px)
        //.error(R.drawable.ic_person_24px)
        .centerCrop()
        .into(view)
}
@BindingAdapter("app:profilePath")
fun bindingProfile(view: ImageView, url: String?) {
    Glide.with(view.context)
        .load(view.context.getString(R.string.tmdb_image_url_original)+url)
        .placeholder(R.drawable.ic_person_24px)
        //.error(R.drawable.ic_person_24px)
        .centerCrop()
        .into(view)
}