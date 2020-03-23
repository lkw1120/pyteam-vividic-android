package com.pyteam.vividic.datasource.entity.movies.details

import com.google.gson.annotations.SerializedName

data class BelongsToCollection (

    @SerializedName("id") val id : String,
    @SerializedName("name") val name : String,
    @SerializedName("poster_path") val posterPath : String,
    @SerializedName("backdrop_path") val backdropPath : String
)