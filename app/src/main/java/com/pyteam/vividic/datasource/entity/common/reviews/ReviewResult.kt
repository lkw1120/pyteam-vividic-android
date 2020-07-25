package com.pyteam.vividic.datasource.entity.common.reviews

import com.google.gson.annotations.SerializedName

data class ReviewResult (

    @SerializedName("id") val id : Int,
    @SerializedName("page") val page : Int,
    @SerializedName("results") val results : List<Review>,
    @SerializedName("total_pages") val total_pages : Int,
    @SerializedName("total_results") val total_results : Int
)