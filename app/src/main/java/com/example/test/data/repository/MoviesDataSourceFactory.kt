package com.example.test.data.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.test.data.api.MovieInterface
import com.example.test.model.Movie
import io.reactivex.disposables.CompositeDisposable


class MoviesDataSourceFactory(
    private val apiService: MovieInterface , private val compositeDisposable: CompositeDisposable
) : DataSource.Factory<Int , Movie>() {

    val moviesLiveDataSource = MutableLiveData<MovieDataSource>()

    override fun create(): DataSource<Int , Movie> {
      val movieDataSource = MovieDataSource(apiService,compositeDisposable)
        moviesLiveDataSource.postValue(movieDataSource)
        return movieDataSource
    }
}