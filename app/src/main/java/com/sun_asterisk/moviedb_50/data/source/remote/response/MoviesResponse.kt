package com.sun_asterisk.moviedb_50.data.source.remote.response

import com.google.gson.annotations.SerializedName
import com.sun_asterisk.moviedb_50.data.model.Movie

data class MoviesResponse(
    @SerializedName(Movie.MovieEntry.PAGE) val moviePage: Int,
    @SerializedName(Movie.MovieEntry.TOTAL_RESULT) val movieTotalResult: Int,
    @SerializedName(Movie.MovieEntry.TOTAL_PAGES) val movieTotalPage: Int,
    @SerializedName(Movie.MovieEntry.MOVIE) val list: List<Movie>
)
