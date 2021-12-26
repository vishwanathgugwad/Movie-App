package com.example.test.data.repository


import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.example.test.data.api.FIRST_PAGE
import com.example.test.data.api.MovieInterface
import com.example.test.model.Movie
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieDataSource(
    private val apiService: MovieInterface , private val compositeDisposable: CompositeDisposable
) : PageKeyedDataSource<Int , Movie>() {

    private var page = FIRST_PAGE
    override fun loadBefore(params: LoadParams<Int> , callback: LoadCallback<Int , Movie>) {
        TODO("Not yet implemented")
    }

    override fun loadInitial(
        params: LoadInitialParams<Int> ,
        callback: LoadInitialCallback<Int , Movie>
    ) {
        compositeDisposable.add(
            apiService.getPopularMovie(page)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        callback.onResult(it.movieresults , null , page+1)
                    },
                    {
                        Log.e("MovieDataSource" , it.message.toString())

                    }
                )
            )

    }

    override fun loadAfter(params: LoadParams<Int> , callback: LoadCallback<Int , Movie>) {

        compositeDisposable.add(
            apiService.getPopularMovie(params.key)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                      if (it.totalPages >= params.key){
                          callback.onResult(it.movieresults , params.key+1)
                      }
                    },
                    {
                        Log.e("MovieDataSource" , it.message.toString())

                    }
                )
        )

    }


}