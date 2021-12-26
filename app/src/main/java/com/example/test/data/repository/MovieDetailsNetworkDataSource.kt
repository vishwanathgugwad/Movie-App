package com.example.test.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.test.data.api.MovieInterface
import com.example.test.model.MovieModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class MovieDetailsNetworkDataSource(
    private val apiService: MovieInterface ,
    private val compositeDisposable: CompositeDisposable
) {



     private val _downloadedMovieDetailsResponse = MutableLiveData<MovieModel>()
    val downloadedMovieDetailsData : LiveData<MovieModel>
        get() = _downloadedMovieDetailsResponse




    fun fetchMovieDetails(movieId : Int){

     try {
         compositeDisposable.add(
             apiService.getMovieDetails(movieId)
                 .subscribeOn(Schedulers.io())
                 .subscribe({
                     _downloadedMovieDetailsResponse.postValue(it)
                 },
                     {
                         Log.e("MovieDetailsDataSource", it.message.toString())
                     }
                 )


         )

     }
     catch (e : Exception){
         Log.e("MovieDetailsDataSource", e.message.toString())

     }

    }

}
