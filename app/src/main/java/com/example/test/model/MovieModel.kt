package com.example.test.model
import com.google.gson.annotations.SerializedName

data class MovieModel(
    @SerializedName("id")
    var id: Int,
    @SerializedName("overview")
    var overview: String,
    @SerializedName("poster_path")
    var posterPath: String,
    @SerializedName("release_date")
    var releaseDate: String,
    @SerializedName("title")
    var title: String,
    @SerializedName("vote_average")
    var rating: Double,

)