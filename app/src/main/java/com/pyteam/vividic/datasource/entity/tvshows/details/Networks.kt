package com.pyteam.vividic.datasource.entity.tvshows.details

import com.google.gson.annotations.SerializedName

data class Networks (

	@SerializedName("name") val name : String,
	@SerializedName("id") val id : String,
	@SerializedName("logo_path") val logoPath : String,
	@SerializedName("origin_country") val originCountry : String
)