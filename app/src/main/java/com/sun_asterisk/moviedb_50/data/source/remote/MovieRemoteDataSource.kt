package com.sun_asterisk.moviedb_50.data.source.remote

import com.sun_asterisk.moviedb_50.data.model.Movie
import com.sun_asterisk.moviedb_50.data.source.MovieDataSource
import com.sun_asterisk.moviedb_50.data.source.remote.api.ApiRequest
import com.sun_asterisk.moviedb_50.data.source.remote.api.NetworkService
import com.sun_asterisk.moviedb_50.data.source.remote.response.GenresResponse
import com.sun_asterisk.moviedb_50.data.source.remote.response.MoviesResponse
import com.sun_asterisk.moviedb_50.utils.Constant
import io.reactivex.Observable

class MovieRemoteDataSource private constructor(private val apiRequest: ApiRequest) :
    MovieDataSource.Remote {

    override fun getGenres(): Observable<GenresResponse> {
        return apiRequest.getGenres()
    }

    override fun getMovies(
        type: String,
        query: String,
        page: Int,
        listener: OnDataLoadedCallback<MoviesResponse>
    ) {
    }

    override fun getMovies(type: String, query: String, page: Int): Observable<MoviesResponse> {
        return when (type) {
            Constant.BASE_GENRES_ID -> apiRequest.getMoviesByGenresID(
                query,
                page
            )
            Constant.BASE_CAST_ID -> apiRequest.getMoviesCastID(
                query,
                page
            )
            Constant.BASE_PRODUCE_ID -> apiRequest.getMoviesByProduceID(
                query,
                page
            )
            Constant.BASE_SEARCH -> apiRequest.getMoviesByQuery(query, page)
            else -> apiRequest.getMoviesByCategory(type, page)
        }
    }

    override fun getMovieDetails(
        movieID: Int
    ): Observable<Movie> {
        return apiRequest.getMovieDetails(movieID)
    }

    companion object {
        private var instance: MovieRemoteDataSource? = null
        fun getInstance() =
            instance ?: MovieRemoteDataSource(NetworkService.getInstance()).also { instance = it }
    }
}
