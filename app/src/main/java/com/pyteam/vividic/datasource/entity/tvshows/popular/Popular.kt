package com.pyteam.vividic.datasource.entity.tvshows.popular

import com.google.gson.annotations.SerializedName
import com.pyteam.vividic.datasource.entity.tvshows.Results

data class Popular (

    @SerializedName("page") val page : Int,
    @SerializedName("total_results") val totalResults : Int,
    @SerializedName("total_pages") val totalPages : Int,
    @SerializedName("results") val results : List<Results>
)