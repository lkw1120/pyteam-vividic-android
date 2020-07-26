package com.pyteam.vividic.datasource.entity.tvshows.seasons

import com.google.gson.annotations.SerializedName

data class GuestStars (

    @SerializedName("id") val id : String,
    @SerializedName("name") val name : String,
    @SerializedName("credit_id") val credit_id : String,
    @SerializedName("character") val character : String,
    @SerializedName("order") val order : String,
    @SerializedName("gender") val gender : String,
    @SerializedName("profile_path") val profile_path : String
)