package com.pyteam.vividic.datasource.entity.tvshows.credits

import com.google.gson.annotations.SerializedName
import com.pyteam.vividic.datasource.entity.common.Cast

data class Cast (

	@SerializedName("character") override val character : String,
	@SerializedName("credit_id") override val creditId : String,
	@SerializedName("id") override val id : Int,
	@SerializedName("name") override val name : String,
	@SerializedName("gender") override val gender : Int,
	@SerializedName("profile_path") override val profilePath : String,
	@SerializedName("order") override val order : Int
): Cast