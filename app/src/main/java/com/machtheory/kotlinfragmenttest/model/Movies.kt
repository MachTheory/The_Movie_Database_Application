package com.machtheory.kotlinfragmenttest.model

import com.google.gson.annotations.SerializedName

data class Movies(

    //Implementing retrofit for backend connectivity with API

    @SerializedName("title")
    val movieName: String?,

    @SerializedName("release_date")
    val releaseDate: String?,

    @SerializedName("runtime")
    val duration: String?,

    @SerializedName("overview")
    val description: String?,

    @SerializedName("vote_average")
    val rating: Double?,

    @SerializedName("poster_path")
    val imageUrl: String?
)