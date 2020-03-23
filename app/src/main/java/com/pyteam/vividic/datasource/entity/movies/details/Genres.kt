package com.pyteam.vividic.datasource.entity.movies.details

import com.google.gson.annotations.SerializedName

data class Genres (

	@SerializedName("id") val id : String,
	@SerializedName("name") val name : String
)