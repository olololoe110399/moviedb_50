package com.sun_asterisk.moviedb_50.data.source

import com.sun_asterisk.moviedb_50.data.model.Category
import com.sun_asterisk.moviedb_50.data.model.Favorite
import com.sun_asterisk.moviedb_50.data.model.Movie
import com.sun_asterisk.moviedb_50.data.source.remote.OnDataLoadedCallback
import com.sun_asterisk.moviedb_50.data.source.remote.response.GenresResponse
import com.sun_asterisk.moviedb_50.data.source.remote.response.MoviesResponse
import io.reactivex.Observable

interface MovieDataSource {
    /**
     * Local
     */
    interface Local {
        fun getCategories(listener: OnDataLoadedCallback<List<Category>>)
        fun getFavorites(listener: OnDataLoadedCallback<MutableList<Favorite>>)
        fun addFavorite(favorite: Favorite, listener: OnDataLoadedCallback<Boolean>)
        fun deleteFavorite(movieID: String, listener: OnDataLoadedCallback<Boolean>)
        fun findFavoriteId(movieID: String, listener: OnDataLoadedCallback<Boolean>)
    }

    /**
     * Remote
     */
    interface Remote {
        fun getGenres(): Observable<GenresResponse>

        fun getMovies(
            type: String,
            query: String,
            page: Int,
            listener: OnDataLoadedCallback<MoviesResponse>
        )

        fun getMovies(
            type: String,
            query: String,
            page: Int
        ): Observable<MoviesResponse>

        fun getMovieDetails(
            movieID: Int
        ): Observable<Movie>
    }
}
