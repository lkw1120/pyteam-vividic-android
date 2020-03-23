package com.pyteam.vividic.datasource.entity.movies.details

import com.google.gson.annotations.SerializedName
import com.pyteam.vividic.datasource.entity.movies.details.*

data class Detail (

    @SerializedName("adult") val adult : Boolean,
    @SerializedName("backdrop_path") val backdropPath : String,
    @SerializedName("belongs_to_collection") val belongsToCollection : BelongsToCollection,
    @SerializedName("budget") val budget : String,
    @SerializedName("genres") val genres : List<Genres>,
    @SerializedName("homepage") val homepage : String,
    @SerializedName("id") val id : String,
    @SerializedName("imdb_id") val imdbId : String,
    @SerializedName("original_language") val originalLanguage : String,
    @SerializedName("original_title") val originalTitle : String,
    @SerializedName("overview") val overview : String,
    @SerializedName("popularity") val popularity : String,
    @SerializedName("poster_path") val posterPath : String,
    @SerializedName("production_companies") val productionCompanies : List<ProductionCompanies>,
    @SerializedName("production_countries") val productionCountries : List<ProductionCountries>,
    @SerializedName("release_date") val releaseDate : String,
    @SerializedName("revenue") val revenue : String,
    @SerializedName("runtime") val runtime : String,
    @SerializedName("spoken_languages") val spokenLanguages : List<SpokenLanguages>,
    @SerializedName("status") val status : String,
    @SerializedName("tagline") val tagline : String,
    @SerializedName("title") val title : String,
    @SerializedName("video") val video : Boolean,
    @SerializedName("vote_average") val voteAverage : String,
    @SerializedName("vote_count") val voteCount : String
)