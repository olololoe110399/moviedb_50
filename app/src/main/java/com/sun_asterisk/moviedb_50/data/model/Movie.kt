package com.sun_asterisk.moviedb_50.data.model

import com.google.gson.annotations.SerializedName
import org.json.JSONObject

data class Movie(
    @SerializedName(MovieEntry.ID)
    val movieID: Int,
    @SerializedName(MovieEntry.TITLE)
    val movieTitle: String,
    @SerializedName(MovieEntry.OVERVIEW)
    val movieOverView: String,
    @SerializedName(MovieEntry.POSTER_PATH)
    val moviePosterPath: String,
    @SerializedName(MovieEntry.BACKDROP_PATH)
    val movieBackdropPath: String,
    @SerializedName(MovieEntry.VOTE_AVERAGE)
    val movieVoteAverage: Double,
    @SerializedName(MovieEntry.RELEASE_DATE)
    val movieReleaseDate: String,
    @SerializedName(Genres.GenresEntry.GENRES) val genres: List<Genres>,
    @SerializedName(Produce.ProduceEntry.PRODUCES) val produce: List<Produce>,
    @SerializedName(Cast.CastEntry.CREDITS) val casts: CastCredits,
    @SerializedName(MovieTrailer.MovieTrailerEntry.MOVIE_TRAILER_VIDEO) val trailers: MovieTrailerVideos
) {
    data class CastCredits(@SerializedName(Cast.CastEntry.CAST) val data: List<Cast>)
    data class MovieTrailerVideos(@SerializedName(MovieTrailer.MovieTrailerEntry.MOVIE_TRAILER_RESULTS) val data: List<MovieTrailer>)
    object MovieEntry {
        const val MOVIE = "results"
        const val ID = "id"
        const val TITLE = "title"
        const val OVERVIEW = "overview"
        const val POSTER_PATH = "poster_path"
        const val BACKDROP_PATH = "backdrop_path"
        const val VOTE_AVERAGE = "vote_average"
        const val RELEASE_DATE = "release_date"
        const val PAGE = "page"
        const val TOTAL_RESULT = "total_results"
        const val TOTAL_PAGES = "total_pages"
    }
}
