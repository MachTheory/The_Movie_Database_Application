package com.machtheory.kotlinfragmenttest.model

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class MoviesAPIService {

    public val BASE_URL = "https://api.themoviedb.org"
    public val apiKey = "333ec19098a2ab59459754b0b6790c4d"
    public var data : List<Movies>? = null
    public var imageData : List<Movies>? = null


    private var retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MoviesAPI::class.java)


    fun getMovies(): List<Movies>? {
        retrofit
            .getMovies()
            .subscribeOn(Schedulers.newThread())
            .unsubscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                    var movieResults = it
                 data = movieResults.results
                Log.i("movie data", data.toString())
            }, {

            })
        return data

    }

    fun getNowPlaying(): List<Movies>? {
        retrofit
            .getNowPlaying()
            .subscribeOn(Schedulers.newThread())
            .unsubscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                var imageResults = it
                imageData = imageResults.results
                Log.i("images data", imageData.toString())
            }, {

            })
        return imageData
    }



}
