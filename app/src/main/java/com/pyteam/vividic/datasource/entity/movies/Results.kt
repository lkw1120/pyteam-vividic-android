package com.pyteam.vividic.datasource.entity.movies

import com.google.gson.annotations.SerializedName

data class Results (

	@SerializedName("popularity") val popularity : String,
	@SerializedName("vote_count") val voteCount : String,
	@SerializedName("video") val video : Boolean,
	@SerializedName("poster_path") val posterPath : String,
	@SerializedName("id") val id : String,
	@SerializedName("adult") val adult : Boolean,
	@SerializedName("backdrop_path") val backdropPath : String,
	@SerializedName("original_language") val originalLanguage : String,
	@SerializedName("original_title") val originalTitle : String,
	@SerializedName("genre_ids") val genreIds : List<String>,
	@SerializedName("title") val title : String,
	@SerializedName("vote_average") val voteAverage : String,
	@SerializedName("overview") val overview : String,
	@SerializedName("release_date") val releaseDate : String
)