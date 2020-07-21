package com.pyteam.vividic.datasource.entity.tvshows

import com.google.gson.annotations.SerializedName

data class TvShowResult (
    @SerializedName("page") val page : String,
    @SerializedName("total_results") val totalResults : String,
    @SerializedName("total_pages") val totalPages : String,
    @SerializedName("results") val results : List<Results>
)