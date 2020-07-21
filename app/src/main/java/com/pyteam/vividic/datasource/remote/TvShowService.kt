package com.pyteam.vividic.datasource.remote

import com.pyteam.vividic.datasource.entity.tvshows.TvShowResult
import com.pyteam.vividic.datasource.entity.tvshows.credits.Credit
import com.pyteam.vividic.datasource.entity.tvshows.details.Detail
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvShowService {

    @GET("search/tv?")
    fun searchTvShow(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
        @Query("language") language: String) : Single<TvShowResult>

    @GET("tv/{tv_id}?")
    fun getDetails(
        @Path("tv_id") tvId: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String) : Single<Detail>

    @GET("tv/{tv_id}/credits?")
    fun getCredits(
        @Path("tv_id") tvId: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String) : Single<Credit>

    @GET("tv/on_the_air?")
    fun getOnTheAir(
        @Query("api_key") apiKey: String,
        @Query("language") language: String) : Single<TvShowResult>

    @GET("tv/popular?")
    fun getPopular(
        @Query("api_key") apiKey: String,
        @Query("language") language: String) : Single<TvShowResult>
}