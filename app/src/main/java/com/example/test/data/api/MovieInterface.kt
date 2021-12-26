package com.example.test.data.api

import com.example.test.model.MovieModel
import com.example.test.model.MovieResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieInterface {


    @GET("movie/popular")
    fun getPopularMovie(@Query("page")page : Int) : Single<MovieResponse>


    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") id : Int) : Single<MovieModel>



}