package com.pyteam.vividic.datasource.entity.movies

import com.google.gson.annotations.SerializedName

data class MovieList (
    @SerializedName("page") val page : String,
    @SerializedName("total_results") val totalResults : String,
    @SerializedName("total_pages") val totalPages : String,
    @SerializedName("results") val movies : List<Movie>
)