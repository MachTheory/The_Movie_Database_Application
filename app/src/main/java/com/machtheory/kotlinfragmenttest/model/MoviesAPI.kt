package com.machtheory.kotlinfragmenttest.model

import io.reactivex.Observable
import retrofit2.http.GET

interface MoviesAPI {

    @GET("/3/movie/popular?api_key=333ec19098a2ab59459754b0b6790c4d&language=en-US&page=1")
    fun getMovies(): Observable<MovieResults>



    //for other endpoints like now playing  or other stuff
    @GET("/3/movie/now_playing?language=en-US&page=undefined&api_key=333ec19098a2ab59459754b0b6790c4d")
    fun getNowPlaying(): Observable<MovieResults>
}
