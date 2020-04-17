package com.sun_asterisk.moviedb_50.data.model

import com.google.gson.annotations.SerializedName

data class Genres(
    @SerializedName(GenresEntry.GENRES_ID)
    val genresID: Int,
    @SerializedName(GenresEntry.GENRES_NAME)
    val genresName: String
) {

    object GenresEntry {
        const val GENRES = "genres"
        const val GENRES_ID = "id"
        const val GENRES_NAME = "name"
    }
}
