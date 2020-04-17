package com.sun_asterisk.moviedb_50.screen.details

import com.sun_asterisk.moviedb_50.data.model.Favorite
import com.sun_asterisk.moviedb_50.data.repository.MovieRepository
import com.sun_asterisk.moviedb_50.data.source.remote.OnDataLoadedCallback
import com.sun_asterisk.moviedb_50.utils.FavoriteEnum
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MovieDetailsPresenter(private val movieRepository: MovieRepository) :
    MovieDetailsContract.Presenter {
    private var view: MovieDetailsContract.View? = null
    private val compositeDisposable = CompositeDisposable()

    override fun onStart() {
    }

    override fun onStop() {
    }

    override fun setView(view: MovieDetailsContract.View?) {
        this.view = view
    }

    override fun getMovieDetails(movieID: Int) {
        view?.onLoading(false)
        val disposable: Disposable = movieRepository.getMovieDetails(movieID)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally {
                view?.onLoading(true)
            }
            .subscribe({ data ->
                view?.run {
                    onGetCastsSuccess(data.casts.data)
                    onGetMovieSuccess(data)
                    onGetProducesSuccess(data.produce)
                    onGetGenresSuccess(data.genres)
                    onGetMovieTrailerSuccess(data.trailers.data)

                }

                isFavoriteMovie(movieID.toString())
            },
                { throwable -> view?.onError(throwable.message.toString()) })
        compositeDisposable.add(disposable)
    }

    private fun isFavoriteMovie(movieID: String) {
        movieRepository.findFavoriteId(movieID, object : OnDataLoadedCallback<Boolean> {
            override fun onSuccess(data: Boolean?) {
                data ?: return
                view?.showFavoriteImage(
                    if (data) FavoriteEnum.ADD_FAVORITE_SUCCESS
                    else FavoriteEnum.DELETE_FAVORITE_SUCCESS
                )
            }

            override fun onError(e: Exception) {
                view?.onError(e.message.toString())
            }

        })
    }

    override fun handleFavorites(favorite: Favorite) {
        movieRepository.findFavoriteId(favorite.movieID, object : OnDataLoadedCallback<Boolean> {
            override fun onSuccess(data: Boolean?) {
                data ?: return
                if (data) deleteFavorite(favorite.movieID) else addFavorite(favorite)
            }

            override fun onError(e: Exception) {
                view?.onError(e.message.toString())
            }
        })
    }

    private fun addFavorite(favorite: Favorite) {
        movieRepository.addFavorite(favorite, object : OnDataLoadedCallback<Boolean> {
            override fun onSuccess(data: Boolean?) {
                data ?: return
                view?.run {
                    if (data) {
                        showFavoriteImage(FavoriteEnum.ADD_FAVORITE_SUCCESS)
                        notifyFavorite(FavoriteEnum.ADD_FAVORITE_SUCCESS)
                    } else {
                        notifyFavorite(FavoriteEnum.ADD_FAVORITE_ERROR)
                    }
                }
            }

            override fun onError(e: Exception) {
                view?.onError(e.message.toString())
            }
        })
    }

    private fun deleteFavorite(movieID: String) {
        movieRepository.deleteFavorite(movieID, object : OnDataLoadedCallback<Boolean> {
            override fun onSuccess(data: Boolean?) {
                data ?: return
                view?.run {
                    if (data) {
                        showFavoriteImage(FavoriteEnum.DELETE_FAVORITE_SUCCESS)
                        notifyFavorite(FavoriteEnum.DELETE_FAVORITE_SUCCESS)
                    } else {
                        notifyFavorite(FavoriteEnum.DELETE_FAVORITE_ERROR)
                    }
                }
            }

            override fun onError(e: Exception) {
                view?.onError(e.message.toString())
            }
        })
    }
}
