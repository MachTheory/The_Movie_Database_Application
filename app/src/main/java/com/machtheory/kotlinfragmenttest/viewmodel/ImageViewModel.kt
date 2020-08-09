package com.machtheory.kotlinfragmenttest.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.machtheory.kotlinfragmenttest.model.Movies
import com.machtheory.kotlinfragmenttest.model.MoviesAPIService
import io.reactivex.disposables.CompositeDisposable

class ImageViewModel : ViewModel() {

    private val imageMovieService = MoviesAPIService()
    private val disposable = CompositeDisposable()


    val images = MutableLiveData<List<Movies>>()

    fun getImage(){
        //fetchFromRemote()
        imageMovieService.getNowPlaying()
        images.value = imageMovieService.imageData
        Log.i("imageURL", images.value.toString())
    }

    private fun fetchFromRemote(){
        disposable.add(
            imageMovieService.getNowPlaying()
        )

    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}

private fun CompositeDisposable.add(nowPlaying: List<Movies>?) {

}



