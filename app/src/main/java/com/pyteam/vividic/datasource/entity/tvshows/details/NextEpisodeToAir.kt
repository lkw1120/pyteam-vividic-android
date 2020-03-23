package com.pyteam.vividic.datasource.entity.tvshows.details

import com.google.gson.annotations.SerializedName

data class NextEpisodeToAir (

	@SerializedName("air_date") val airDate : String,
	@SerializedName("episode_number") val episodeNumber : String,
	@SerializedName("id") val id : String,
	@SerializedName("name") val name : String,
	@SerializedName("overview") val overview : String,
	@SerializedName("production_code") val productionCode : String,
	@SerializedName("season_number") val seasonNumber : String,
	@SerializedName("show_id") val showId : String,
	@SerializedName("still_path") val stillPath : String,
	@SerializedName("vote_average") val voteAverage : String,
	@SerializedName("vote_count") val voteCount : String
)