package com.sun_asterisk.moviedb_50.data.source.remote.api

import com.sun_asterisk.moviedb_50.data.model.Movie
import com.sun_asterisk.moviedb_50.data.source.remote.response.GenresResponse
import com.sun_asterisk.moviedb_50.data.source.remote.response.MoviesResponse
import com.sun_asterisk.moviedb_50.utils.Constant
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiRequest {

    @GET(Constant.BASE_GENRES_LIST + Constant.BASE_API_KEY + Constant.BASE_LANGUAGE)
    fun getGenres(): Observable<GenresResponse>

    @GET(
        Constant.BASE_MOVIE +
                "{${Constant.BASE_TYPE}}" +
                Constant.BASE_API_KEY +
                Constant.BASE_LANGUAGE
    )
    fun getMoviesByCategory(
        @Path(Constant.BASE_TYPE) type: String,
        @Query(Constant.BASE_PAGE) page: Int
    ): Observable<MoviesResponse>

    @GET(
        Constant.BASE_DISCOVER_MOVIE +
                Constant.BASE_API_KEY +
                Constant.BASE_LANGUAGE
    )
    fun getMoviesByGenresID(
        @Query(Constant.BASE_GENRES_ID) query: String,
        @Query(Constant.BASE_PAGE) page: Int
    ): Observable<MoviesResponse>

    @GET(
        Constant.BASE_DISCOVER_MOVIE +
                Constant.BASE_API_KEY +
                Constant.BASE_LANGUAGE
    )
    fun getMoviesCastID(
        @Query(Constant.BASE_CAST_ID) query: String,
        @Query(Constant.BASE_PAGE) page: Int
    ): Observable<MoviesResponse>

    @GET(
        Constant.BASE_DISCOVER_MOVIE +
                Constant.BASE_API_KEY +
                Constant.BASE_LANGUAGE
    )
    fun getMoviesByProduceID(
        @Query(Constant.BASE_PRODUCE_ID) query: String,
        @Query(Constant.BASE_PAGE) page: Int
    ): Observable<MoviesResponse>

    @GET(Constant.BASE_SEARCH + Constant.BASE_API_KEY + Constant.BASE_LANGUAGE)
    fun getMoviesByQuery(
        @Query(Constant.BASE_QUERY) query: String,
        @Query(Constant.BASE_PAGE) page: Int
    ): Observable<MoviesResponse>

    @GET(
        Constant.BASE_MOVIE +
                "{${Constant.BASE_QUERY}}" +
                Constant.BASE_API_KEY +
                Constant.BASE_LANGUAGE +
                Constant.BASE_APPEND +
                Constant.BASE_CREDITS +
                Constant.BASE_VIDEO
    )
    fun getMovieDetails(@Path(Constant.BASE_QUERY) query: Int): Observable<Movie>
}
