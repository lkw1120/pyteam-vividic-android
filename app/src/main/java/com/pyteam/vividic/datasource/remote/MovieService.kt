package com.pyteam.vividic.datasource.remote

import com.pyteam.vividic.datasource.entity.common.reviews.ReviewResult
import com.pyteam.vividic.datasource.entity.movies.MovieResult
import com.pyteam.vividic.datasource.entity.movies.credits.Credit
import com.pyteam.vividic.datasource.entity.movies.details.Detail
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("search/movie?")
    fun searchMovie(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
        @Query("language") language: String) : Single<MovieResult>

    @GET("movie/{movie_id}?")
    fun getDetails(
        @Path("movie_id") movieId: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String) : Single<Detail>

    @GET("movie/{movie_id}/credits?")
    fun getCredits(
        @Path("movie_id") movieId: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String) : Single<Credit>

    @GET("movie/{movie_id}/reviews?")
    fun getReviews(
        @Path("movie_id") movieId: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String) : Single<ReviewResult>

    @GET("movie/{movie_id}/similar?")
    fun getSimilar(
        @Path("movie_id") movieId: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String) : Single<MovieResult>

    @GET("movie/now_playing?")
    fun getNowPlaying(
        @Query("api_key") apiKey: String,
        @Query("language") language: String) : Single<MovieResult>

    @GET("movie/popular?")
    fun getPopular(
        @Query("api_key") apiKey: String,
        @Query("language") language: String) : Single<MovieResult>
}