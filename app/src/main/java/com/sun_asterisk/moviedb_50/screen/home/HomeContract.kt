package com.sun_asterisk.moviedb_50.screen.home

import com.sun_asterisk.moviedb_50.data.model.Genres
import com.sun_asterisk.moviedb_50.data.model.Movie
import com.sun_asterisk.moviedb_50.screen.base.BasePresenter
import com.sun_asterisk.moviedb_50.utils.Constant
import com.sun_asterisk.moviedb_50.utils.MovieCategoryEnum

interface HomeContract {
    /**
     * View
     */
    interface View {
        fun onGetGenresSuccess(genres: List<Genres>)
        fun onGetMovies(type: MovieCategoryEnum, movies: List<Movie>)
        fun onError(str: String?)
        fun onLoading(isLoad: Boolean)
    }

    /**
     * Presenter
     */
    interface Presenter : BasePresenter<View?> {
        fun getGenres()
        fun getMovie(
            type: String,
            query: String = Constant.BASE_QUERY_DEFAULT,
            page: Int = Constant.BASE_PAGE_DEFAULT
        )
    }
}
