package com.pyteam.vividic.datasource.entity.tvshows.details

import com.google.gson.annotations.SerializedName

data class ProductionCompanies (

	@SerializedName("id") val id : String,
	@SerializedName("logo_path") val logoPath : String,
	@SerializedName("name") val name : String,
	@SerializedName("origin_country") val originCountry : String
)