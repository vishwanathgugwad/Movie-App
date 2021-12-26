package com.example.test.ui.single_movie_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.test.model.MovieModel
import io.reactivex.disposables.CompositeDisposable

class SingleMovieViewModel(movieRepository: MovieDetailsRepository , movieId: Int) :
    ViewModel() {


    private val compositeDisposable = CompositeDisposable()

    val movieDetails : LiveData<MovieModel>  by lazy {
        movieRepository.fetchSingleMovieDetails(
            compositeDisposable ,
            movieId
        )
    }



    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}