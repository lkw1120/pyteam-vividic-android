package com.pyteam.vividic.datasource.remote

import android.content.Context
import com.pyteam.vividic.R
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConnection(context: Context) {
    private val baseUrl: String = context.getString(R.string.tmdb_api_url)
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getMovies(): MovieService =
        retrofit.create(MovieService::class.java)

    fun getTvShows(): TvShowService =
        retrofit.create(TvShowService::class.java)
}