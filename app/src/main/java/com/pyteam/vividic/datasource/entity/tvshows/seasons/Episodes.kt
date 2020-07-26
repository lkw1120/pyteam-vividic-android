package com.pyteam.vividic.datasource.entity.tvshows.seasons

import com.google.gson.annotations.SerializedName

data class Episodes (

    @SerializedName("air_date") val air_date : String,
    @SerializedName("episode_number") val episode_number : String,
    @SerializedName("id") val id : String,
    @SerializedName("name") val name : String,
    @SerializedName("overview") val overview : String,
    @SerializedName("production_code") val production_code : String,
    @SerializedName("season_number") val season_number : String,
    @SerializedName("show_id") val show_id : String,
    @SerializedName("still_path") val still_path : String,
    @SerializedName("vote_average") val vote_average : String,
    @SerializedName("vote_count") val vote_count : String,
    //@SerializedName("crew") val crew : List<String>,
    @SerializedName("guest_stars") val guest_stars : List<GuestStars>
)