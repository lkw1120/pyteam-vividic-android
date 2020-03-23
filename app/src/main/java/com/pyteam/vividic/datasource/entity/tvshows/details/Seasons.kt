package com.pyteam.vividic.datasource.entity.tvshows.details

import com.google.gson.annotations.SerializedName

data class Seasons (

	@SerializedName("air_date") val airDate : String,
	@SerializedName("episode_count") val episodeCount : String,
	@SerializedName("id") val id : String,
	@SerializedName("name") val name : String,
	@SerializedName("overview") val overview : String,
	@SerializedName("poster_path") val posterPath : String,
	@SerializedName("season_number") val seasonNumber : String
)