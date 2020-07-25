package com.pyteam.vividic.datasource.entity.common.credits

import com.google.gson.annotations.SerializedName

data class Credit (

    @SerializedName("id") val id : String,
    @SerializedName("cast") val casts : List<Cast>,
    @SerializedName("crew") val crews : List<Crew>
)