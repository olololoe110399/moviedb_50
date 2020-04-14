package com.sun_asterisk.moviedb_50.data.repository

import com.sun_asterisk.moviedb_50.data.model.Category
import com.sun_asterisk.moviedb_50.data.model.Favorite
import com.sun_asterisk.moviedb_50.data.model.Movie
import com.sun_asterisk.moviedb_50.data.source.MovieDataSource
import com.sun_asterisk.moviedb_50.data.source.remote.MovieRemoteDataSource
import com.sun_asterisk.moviedb_50.data.source.remote.OnDataLoadedCallback
import com.sun_asterisk.moviedb_50.data.source.remote.response.GenresResponse
import com.sun_asterisk.moviedb_50.data.source.remote.response.MoviesResponse
import io.reactivex.Observable

class MovieRepository private constructor(
    private val remoteMovie: MovieDataSource.Remote,
    private val localMovie: MovieDataSource.Local
) {
    fun getCategories(listener: OnDataLoadedCallback<List<Category>>) {
        localMovie.getCategories(listener)
    }

    fun getGenres(): Observable<GenresResponse> {
        return remoteMovie.getGenres()
    }

    fun getMovies(
        type: String,
        query: String,
        page: Int
    ): Observable<MoviesResponse> {
        return  remoteMovie.getMovies(type, query, page)
    }

    fun getMovieDetails(
        movieID: Int
    ):Observable<Movie> {
        return remoteMovie.getMovieDetails(movieID)
    }
    fun getFavorites(listener: OnDataLoadedCallback<MutableList<Favorite>>) {
        localMovie.getFavorites(listener)
    }

    fun addFavorite(favorite: Favorite, listener: OnDataLoadedCallback<Boolean>) {
        localMovie.addFavorite(favorite, listener)
    }

    fun deleteFavorite(movieID: String, listener: OnDataLoadedCallback<Boolean>) {
        localMovie.deleteFavorite(movieID, listener)
    }

    fun findFavoriteId(movieID: String, listener: OnDataLoadedCallback<Boolean>) {
        localMovie.findFavoriteId(movieID, listener)
    }

    companion object {
        private var instance: MovieRepository? = null
        fun getInstance(
            movieRemoteDataSource: MovieRemoteDataSource,
            localMovieDataSource: MovieDataSource.Local
        ) = instance ?: MovieRepository(
            movieRemoteDataSource,
            localMovieDataSource
        ).also { instance = it }
    }
}
