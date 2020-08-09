package com.machtheory.kotlinfragmenttest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.machtheory.kotlinfragmenttest.model.Movies
import com.machtheory.kotlinfragmenttest.model.MoviesAPIService

class DetailViewModel : ViewModel() {
    private val movieService = MoviesAPIService()

    var movie = MutableLiveData<List<Movies>>()

    var singleMovie = MutableLiveData<Movies>()

    fun getMovie(){
        movie.value = movieService.getMovies()

    }

}