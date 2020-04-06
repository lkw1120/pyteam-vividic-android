package com.pyteam.vividic.datasource.remote

import com.pyteam.vividic.datasource.entity.people.details.Detail
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
}