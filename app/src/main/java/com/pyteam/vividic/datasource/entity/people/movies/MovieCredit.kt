package com.pyteam.vividic.datasource.entity.people.movies

import com.google.gson.annotations.SerializedName
import com.pyteam.vividic.datasource.entity.movies.Movie

data class MovieCredit (

	@SerializedName("id") val id : String,
	@SerializedName("cast") val casts : List<Movie>,
	@SerializedName("crew") val crews : List<Movie>
)