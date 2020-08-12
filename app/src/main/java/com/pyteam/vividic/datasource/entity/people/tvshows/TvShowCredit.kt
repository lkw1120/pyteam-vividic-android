package com.pyteam.vividic.datasource.entity.people.tvshows

import com.google.gson.annotations.SerializedName
import com.pyteam.vividic.datasource.entity.tvshows.TvShow

data class TvShowCredit (

	@SerializedName("id") val id : String,
	@SerializedName("cast") val casts : List<TvShow>,
	@SerializedName("crew") val crews : List<TvShow>
)