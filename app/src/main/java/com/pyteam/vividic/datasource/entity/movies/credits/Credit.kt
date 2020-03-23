package com.pyteam.vividic.datasource.entity.movies.credits

import com.google.gson.annotations.SerializedName

data class Credit (

	@SerializedName("id") val id : String,
	@SerializedName("cast") val cast : List<Cast>,
	@SerializedName("crew") val crew : List<Crew>
)