package com.pyteam.vividic.datasource.entity.common.credits

import com.google.gson.annotations.SerializedName

data class Cast (

    @SerializedName("character") val character : String,
    @SerializedName("credit_id") val creditId : String,
    @SerializedName("gender") val gender : String,
    @SerializedName("id") val id : String,
    @SerializedName("name") val name : String,
    @SerializedName("order") val order : String,
    @SerializedName("profile_path") val profilePath : String
)