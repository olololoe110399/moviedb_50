package com.sun_asterisk.moviedb_50.screen.home

import com.sun_asterisk.moviedb_50.data.repository.MovieRepository
import com.sun_asterisk.moviedb_50.utils.Constant
import com.sun_asterisk.moviedb_50.utils.MovieCategoryEnum
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class HomePresenter(private val movieRepository: MovieRepository) : HomeContract.Presenter {
    private var view: HomeContract.View? = null
    private val compositeDisposable = CompositeDisposable()

    override fun onStart() {
        view?.onLoading(false)
        getGenres()
        getMovie(Constant.BASE_NOW_PLAYING)
        getMovie(Constant.BASE_UPCOMING)
        getMovie(Constant.BASE_POPULAR)
    }

    override fun onStop() {}

    override fun setView(view: HomeContract.View?) {
        this.view = view
    }

    override
    fun getGenres() {
        val disposable: Disposable = movieRepository.getGenres()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ genreResponse ->
                view?.onGetGenresSuccess(genreResponse.list)
            },
                { throwable -> view?.onError(throwable.message.toString()) })
        compositeDisposable.add(disposable)
    }

    override fun getMovie(type: String, query: String, page: Int) {


        val disposable: Disposable = movieRepository.getMovies(type, query, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally {
                view?.onLoading(true)
            }
            .subscribe({ data ->
                when (type) {
                    Constant.BASE_NOW_PLAYING ->
                        view?.onGetMovies(
                            MovieCategoryEnum.NOW_PLAYING,
                            data.list
                        )
                    Constant.BASE_UPCOMING ->
                        view?.onGetMovies(MovieCategoryEnum.UPCOMING, data.list)
                    Constant.BASE_POPULAR ->
                        view?.onGetMovies(MovieCategoryEnum.POPULAR, data.list)
                    Constant.BASE_GENRES_ID ->
                        view?.onGetMovies(MovieCategoryEnum.BY_GENRES, data.list)
                }
            },
                { throwable -> view?.onError(throwable.message.toString()) })
        compositeDisposable.add(disposable)
    }
}
