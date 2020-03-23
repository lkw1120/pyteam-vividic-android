package com.pyteam.vividic.datasource.entity.movies.credits

import com.google.gson.annotations.SerializedName
import com.pyteam.vividic.datasource.entity.common.Crew

data class Crew (

	@SerializedName("credit_id") override val creditId : String,
	@SerializedName("department") override val department : String,
	@SerializedName("gender") override val gender : String,
	@SerializedName("id") override val id : String,
	@SerializedName("job") override val job : String,
	@SerializedName("name") override val name : String,
	@SerializedName("profile_path") override val profilePath : String
): Crew