package com.pyteam.vividic.datasource.entity.movies.nowplaying


import com.google.gson.annotations.SerializedName
import com.pyteam.vividic.datasource.entity.movies.Results


data class NowPlaying (

	@SerializedName("results") val results : List<Results>,
	@SerializedName("page") val page : Int,
	@SerializedName("total_results") val totalResults : Int,
	@SerializedName("dates") val dates : Dates,
	@SerializedName("total_pages") val totalPages : Int
)