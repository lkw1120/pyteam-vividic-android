package com.pyteam.vividic.datasource.entity.movies.credits

import com.google.gson.annotations.SerializedName
import com.pyteam.vividic.datasource.entity.common.Cast

data class Cast (

	@SerializedName("cast_id") val castId : String,
	@SerializedName("character") override val character : String,
	@SerializedName("credit_id") override val creditId : String,
	@SerializedName("gender") override val gender : String,
	@SerializedName("id") override val id : String,
	@SerializedName("name") override val name : String,
	@SerializedName("order") override val order : String,
	@SerializedName("profile_path") override val profilePath : String
): Cast