package com.pyteam.vividic.datasource.entity.tvshows

import com.google.gson.annotations.SerializedName

data class Results (

	@SerializedName("original_name") val originalName : String,
	@SerializedName("genre_ids") val genreIds : List<String>,
	@SerializedName("name") val name : String,
	@SerializedName("popularity") val popularity : String,
	@SerializedName("origin_country") val originCountry : List<String>,
	@SerializedName("vote_count") val voteCount : String,
	@SerializedName("first_air_date") val firstAirDate : String,
	@SerializedName("backdrop_path") val backdropPath : String,
	@SerializedName("original_language") val originalLanguage : String,
	@SerializedName("id") val id : String,
	@SerializedName("vote_average") val voteAverage : String,
	@SerializedName("overview") val overview : String,
	@SerializedName("poster_path") val posterPath : String
)