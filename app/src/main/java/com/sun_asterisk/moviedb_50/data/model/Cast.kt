package com.sun_asterisk.moviedb_50.data.model

import com.google.gson.annotations.SerializedName

data class Cast(
    @SerializedName(CastEntry.ID)
    val castId: Int,
    @SerializedName(CastEntry.NAME)
    val castName: String,
    @SerializedName(CastEntry.PROFILE_PATH)
    val castProfilePath: String
) {

    object CastEntry {
        const val CREDITS = "credits"
        const val CAST = "cast"
        const val ID = "id"
        const val NAME = "name"
        const val PROFILE_PATH = "profile_path"
    }
}
