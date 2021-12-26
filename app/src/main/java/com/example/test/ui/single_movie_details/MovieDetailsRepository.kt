package com.example.test.ui.single_movie_details

import androidx.lifecycle.LiveData
import com.example.test.data.api.MovieInterface
import com.example.test.model.MovieModel
import com.example.test.data.repository.MovieDetailsNetworkDataSource
import io.reactivex.disposables.CompositeDisposable

class MovieDetailsRepository(private val apiService : MovieInterface) {

    lateinit var movieDetailsNetworkDataSource: MovieDetailsNetworkDataSource

    fun fetchSingleMovieDetails(compositeDisposable: CompositeDisposable , movieId : Int) : LiveData<MovieModel> {

        movieDetailsNetworkDataSource = MovieDetailsNetworkDataSource(apiService,compositeDisposable)
        movieDetailsNetworkDataSource.fetchMovieDetails(movieId)

        return movieDetailsNetworkDataSource.downloadedMovieDetailsData

    }

}