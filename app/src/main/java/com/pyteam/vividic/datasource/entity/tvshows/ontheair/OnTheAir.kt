package com.pyteam.vividic.datasource.entity.tvshows.ontheair

import com.google.gson.annotations.SerializedName
import com.pyteam.vividic.datasource.entity.tvshows.Results

data class OnTheAir (

	@SerializedName("page") val page : String,
	@SerializedName("total_results") val total_results : String,
	@SerializedName("total_pages") val total_pages : String,
	@SerializedName("results") val results : List<Results>
)