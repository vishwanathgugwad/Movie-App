package com.example.test.model


import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("page")
    var page: Int ,
    @SerializedName("results")
    var movieresults: List<Movie> ,
    @SerializedName("total_pages")
    var totalPages: Int ,
    @SerializedName("total_results")
    var totalResults: Int
)