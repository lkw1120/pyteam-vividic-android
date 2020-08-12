package com.pyteam.vividic.datasource.remote

import com.pyteam.vividic.datasource.entity.people.details.Detail
import com.pyteam.vividic.datasource.entity.people.movies.MovieCredit
import com.pyteam.vividic.datasource.entity.people.tvshows.TvShowCredit
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PersonService {

    @GET("person/{person_id}?")
    fun getDetails(
        @Path("person_id") personId: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String) : Single<Detail>

    @GET("person/{person_id}/movie_credits?")
    fun getMovieCredit(
        @Path("person_id") personId: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String) : Single<MovieCredit>

    @GET("person/{person_id}/tv_credits?")
    fun getTvShowCredit(
        @Path("person_id") personId: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String) : Single<TvShowCredit>

}