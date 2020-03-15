package com.pyteam.vividic.datasource.entity.movies.popular

import com.google.gson.annotations.SerializedName
import com.pyteam.vividic.datasource.entity.movies.Results

data class Popular (

	@SerializedName("page") val page : Int,
	@SerializedName("total_results") val totalResults : Int,
	@SerializedName("total_pages") val totalPages : Int,
	@SerializedName("results") val results : List<Results>
)