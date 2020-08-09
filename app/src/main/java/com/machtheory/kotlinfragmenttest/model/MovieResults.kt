package com.machtheory.kotlinfragmenttest.model

import com.google.gson.annotations.SerializedName

class MovieResults (

    @SerializedName("results")
    var results: List<Movies>

)

