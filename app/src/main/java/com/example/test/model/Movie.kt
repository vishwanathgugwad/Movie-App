package com.example.test.model


import com.google.gson.annotations.SerializedName

data class Movie(

    @SerializedName("id")
    var id: Int,
    @SerializedName("poster_path")
    var posterPath: String,


)