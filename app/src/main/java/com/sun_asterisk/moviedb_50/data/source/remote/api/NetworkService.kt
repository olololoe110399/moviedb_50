package com.sun_asterisk.moviedb_50.data.source.remote.api

import com.sun_asterisk.moviedb_50.utils.Constant
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NetworkService {
    companion object {
        private var apiRequest: ApiRequest? = null
        fun getInstance(): ApiRequest =
            apiRequest ?: retrofitBuilder().create(ApiRequest::class.java)
                .also { apiRequest = it }

        private fun retrofitBuilder() = Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
