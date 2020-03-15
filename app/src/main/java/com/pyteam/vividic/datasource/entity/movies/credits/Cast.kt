package com.pyteam.vividic.datasource.entity.movies.credits

import com.google.gson.annotations.SerializedName
import com.pyteam.vividic.datasource.entity.common.Cast

data class Cast (

	@SerializedName("cast_id") val castId : Int,
	@SerializedName("character") override val character : String,
	@SerializedName("credit_id") override val creditId : String,
	@SerializedName("gender") override val gender : Int,
	@SerializedName("id") override val id : Int,
	@SerializedName("name") override val name : String,
	@SerializedName("order") override val order : Int,
	@SerializedName("profile_path") override val profilePath : String
): Cast