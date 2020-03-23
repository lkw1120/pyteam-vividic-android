package com.pyteam.vividic.datasource.entity.tvshows.popular

import com.google.gson.annotations.SerializedName
import com.pyteam.vividic.datasource.entity.tvshows.Results

data class Popular (

    @SerializedName("page") val page : String,
    @SerializedName("total_results") val totalResults : String,
    @SerializedName("total_pages") val totalPages : String,
    @SerializedName("results") val results : List<Results>
)