package com.pyteam.vividic.datasource.entity.tvshows.details

import com.google.gson.annotations.SerializedName

data class Detail (

    @SerializedName("backdrop_path") val backdropPath : String,
    @SerializedName("created_by") val createdBy : List<CreatedBy>,
    @SerializedName("episode_run_time") val episodeRunTime : List<String>,
    @SerializedName("first_air_date") val firstAirDate : String,
    @SerializedName("genres") val genres : List<Genres>,
    @SerializedName("homepage") val homepage : String,
    @SerializedName("id") val id : String,
    @SerializedName("in_production") val inProduction : Boolean,
    @SerializedName("languages") val languages : List<String>,
    @SerializedName("last_air_date") val lastAirDate : String,
    @SerializedName("last_episode_to_air") val lastEpisodeToAir : LastEpisodeToAir,
    @SerializedName("name") val name : String,
    @SerializedName("next_episode_to_air") val nextEpisodeToAir : NextEpisodeToAir,
    @SerializedName("networks") val networks : List<Networks>,
    @SerializedName("number_of_episodes") val numberOfEpisodes : String,
    @SerializedName("number_of_seasons") val numberOfSeasons : String,
    @SerializedName("origin_country") val originCountry : List<String>,
    @SerializedName("original_language") val originalLanguage : String,
    @SerializedName("original_name") val originalName : String,
    @SerializedName("overview") val overview : String,
    @SerializedName("popularity") val popularity : String,
    @SerializedName("poster_path") val posterPath : String,
    @SerializedName("production_companies") val productionCompanies : List<ProductionCompanies>,
    @SerializedName("seasons") val seasons : List<Seasons>,
    @SerializedName("status") val status : String,
    @SerializedName("type") val type : String,
    @SerializedName("vote_average") val voteAverage : String,
    @SerializedName("vote_count") val voteCount : String
)