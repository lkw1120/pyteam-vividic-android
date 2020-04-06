package com.pyteam.vividic.datasource.entity.people.details

import com.google.gson.annotations.SerializedName

data class Detail (

    @SerializedName("birthday") val birthday : String,
    @SerializedName("known_for_department") val knownForDepartment : String,
    @SerializedName("deathday") val deathday : String,
    @SerializedName("id") val id : String,
    @SerializedName("name") val name : String,
    @SerializedName("also_known_as") val alsoKnownAs : List<String>,
    @SerializedName("gender") val gender : String,
    @SerializedName("biography") val biography : String,
    @SerializedName("popularity") val popularity : String,
    @SerializedName("place_of_birth") val placeOfBirth : String,
    @SerializedName("profile_path") val profilePath : String,
    @SerializedName("adult") val adult : Boolean,
    @SerializedName("imdb_id") val imdbId : String,
    @SerializedName("homepage") val homepage : String
)