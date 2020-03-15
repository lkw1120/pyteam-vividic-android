package com.pyteam.vividic.datasource.remote

import com.pyteam.vividic.datasource.entity.movies.credits.Credit
import com.pyteam.vividic.datasource.entity.movies.details.Detail
import com.pyteam.vividic.datasource.entity.movies.nowplaying.NowPlaying
import com.pyteam.vividic.datasource.entity.movies.popular.Popular
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("movie/{movie_id}?")
    fun getDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String) : Call<Detail>

    @GET("movie/{movie_id}/credits?")
    fun getCredits(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String) : Call<Credit>

    @GET("movie/now_playing?")
    fun getNowPlaying(
        @Query("api_key") apiKey: String,
        @Query("language") language: String) : Call<NowPlaying>

    @GET("movie/popular?")
    fun getPopular(
        @Query("api_key") apiKey: String,
        @Query("language") language: String) : Call<Popular>
}