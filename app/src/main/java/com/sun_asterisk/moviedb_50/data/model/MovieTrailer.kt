package com.sun_asterisk.moviedb_50.data.model

import com.google.gson.annotations.SerializedName

data class MovieTrailer(

    @SerializedName(MovieTrailerEntry.MOVIE_TRAILER_ID) val movieTrailerID: String,
    @SerializedName(MovieTrailerEntry.MOVIE_TRAILER_KEY) val movieTrailerKey: String,
    @SerializedName(MovieTrailerEntry.MOVIE_TRAILER_NAME) val movieTrailerName: String
) {
    object MovieTrailerEntry {
        const val MOVIE_TRAILER_VIDEO = "videos"
        const val MOVIE_TRAILER_RESULTS = "results"
        const val MOVIE_TRAILER_ID = "id"
        const val MOVIE_TRAILER_KEY = "key"
        const val MOVIE_TRAILER_NAME = "name"
    }
}
