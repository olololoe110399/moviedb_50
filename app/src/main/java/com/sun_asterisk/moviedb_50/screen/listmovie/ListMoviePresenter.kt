package com.sun_asterisk.moviedb_50.screen.listmovie

import com.sun_asterisk.moviedb_50.data.model.Movie
import com.sun_asterisk.moviedb_50.data.repository.MovieRepository
import com.sun_asterisk.moviedb_50.utils.Constant
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ListMoviePresenter(private val movieRepository: MovieRepository) :
    ListMovieContract.Presenter {
    private var view: ListMovieContract.View? = null
    private val compositeDisposable = CompositeDisposable()

    override fun setView(view: ListMovieContract.View?) {
        this.view = view
    }

    override fun getMovies(type: String, query: String, page: Int) {
        if (page == Constant.BASE_PAGE_DEFAULT) {
            view?.onLoading(false)
        }


        val disposable: Disposable = movieRepository.getMovies(type, query, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ data ->
                view?.onGetMovieResultPage(data.movieTotalResult, data.movieTotalPage)
                view?.onGetMoviesSuccess(data.list)
                view?.onLoading(true)
            },
                { throwable -> view?.onError(throwable.message.toString()) })
        compositeDisposable.add(disposable)
    }

    override fun onStart() {
        TODO("Not yet implemented")
    }

    override fun onStop() {
        TODO("Not yet implemented")
    }
}
