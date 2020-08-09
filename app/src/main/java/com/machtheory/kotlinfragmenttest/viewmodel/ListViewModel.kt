package com.machtheory.kotlinfragmenttest.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.machtheory.kotlinfragmenttest.model.Movies
import com.machtheory.kotlinfragmenttest.model.MoviesAPIService


class ListViewModel : ViewModel() {

    private val movieService = MoviesAPIService()

    var movie = MutableLiveData<List<Movies>>()
    val movieLoadError = MutableLiveData<Boolean>()
    val moviesLoading = MutableLiveData<Boolean>()

    fun refresh() {
        fetchFromRemote()
        movieService.getMovies()
        movie.value = movieService.data
        Log.i("movie", movieService.data.toString())

    }

    private fun fetchFromRemote() {
        moviesLoading.value = false


    }
}




