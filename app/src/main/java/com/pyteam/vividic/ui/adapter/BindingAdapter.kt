package com.pyteam.vividic.ui.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.pyteam.vividic.R

@BindingAdapter("app:posterPath")
fun bindingPoster(view: ImageView, url: String?) {
    Glide.with(view.context)
        .load(view.context.getString(R.string.tmdb_image_url_original)+url)
        .placeholder(R.drawable.ic_image_24px)
        //.error(R.drawable.ic_broken_image_24px)
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