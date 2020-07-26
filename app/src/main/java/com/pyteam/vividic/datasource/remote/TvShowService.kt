package com.pyteam.vividic.datasource.remote

import com.pyteam.vividic.datasource.entity.common.credits.Credit
import com.pyteam.vividic.datasource.entity.common.reviews.ReviewList
import com.pyteam.vividic.datasource.entity.tvshows.TvShowList
import com.pyteam.vividic.datasource.entity.tvshows.details.Detail
import com.pyteam.vividic.datasource.entity.tvshows.seasons.Season
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvShowService {

    @GET("search/tv?")
    fun searchTvShow(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
        @Query("language") language: String) : Single<TvShowList>

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

    @GET("tv/{tv_id}/reviews?")
    fun getReviews(
        @Path("tv_id") tvId: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String) : Single<ReviewList>

    @GET("tv/{tv_id}/similar?")
    fun getSimilar(
        @Path("tv_id") tvId: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String) : Single<TvShowList>

    @GET("tv/{tv_id}/season/{season_number}?")
    fun getSeason(
        @Path("tv_id") tvId: String,
        @Path("season_number") seasonNumber: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String) : Single<Season>

    @GET("tv/on_the_air?")
    fun getOnTheAir(
        @Query("api_key") apiKey: String,
        @Query("language") language: String) : Single<TvShowList>

    @GET("tv/popular?")
    fun getPopular(
        @Query("api_key") apiKey: String,
        @Query("language") language: String) : Single<TvShowList>

}