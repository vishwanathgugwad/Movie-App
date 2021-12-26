package com.example.test.ui.mainpagemovies

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.test.data.api.MovieInterface
import com.example.test.data.api.POST_PER_PAGE
import com.example.test.data.repository.MoviesDataSourceFactory
import com.example.test.model.Movie
import io.reactivex.disposables.CompositeDisposable

class MoviePageListRepository(private val apiService : MovieInterface) {

    lateinit var moviePagedList : LiveData<PagedList<Movie>>
    lateinit var movieDataSourceFactory : MoviesDataSourceFactory

    fun fetchLiveMoviePageList(compositeDisposable: CompositeDisposable) : LiveData<PagedList<Movie>>{

        movieDataSourceFactory = MoviesDataSourceFactory(apiService,compositeDisposable)

        val config : PagedList.Config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(POST_PER_PAGE)
            .build()


        moviePagedList = LivePagedListBuilder(movieDataSourceFactory, config).build()

        return moviePagedList
    }
}