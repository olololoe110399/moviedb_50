package com.sun_asterisk.moviedb_50.data.source

import com.sun_asterisk.moviedb_50.data.model.Genres
import com.sun_asterisk.moviedb_50.data.model.Movie
import com.sun_asterisk.moviedb_50.data.source.remote.OnFetchDataJsonListener


interface DataSource {
    /**
     * Local
     */
    interface LocalDataSource {

    }

    /**
     * Remote
     */
    interface RemoteDataSource {
        fun getGenres(onFetchDataJsonListener: OnFetchDataJsonListener<Genres>?)
        fun getMovie(onFetchDataJsonListener: OnFetchDataJsonListener<Movie>?, type: String)
    }
}