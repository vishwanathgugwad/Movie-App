package com.example.test.data.api


import okhttp3.Interceptor
import okhttp3.OkHttpClient

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

//https://api.themoviedb.org/3/movie/500?api_key=b5380f5f79de9c0f608433500b1a40a9

const val BASE_URL = "https://api.themoviedb.org/3/"
const val API_KEY = "b5380f5f79de9c0f608433500b1a40a9"
const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/w500"

const val   FIRST_PAGE = 1
const val   POST_PER_PAGE = 20

object MovieClient {

    fun getClient(): MovieInterface {
        val requestInterceptor = Interceptor { chain ->
            //Interceptor takes only one argument which is a lambda function so parenthesis can be omitted
            val url =
                chain.request()
                    .url
                    .newBuilder()
                    .addQueryParameter("api_key" , API_KEY)
                    .build()

            val request = chain.request()
                .newBuilder()
                .url(url)
                .build()
            return@Interceptor chain.proceed(request) //explicitly return a value from whit @ annotation. lambda
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .connectTimeout(60 , TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieInterface::class.java)

    }
}