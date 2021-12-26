package com.example.test.ui.mainpagemovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.test.data.api.MovieClient
import com.example.test.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private lateinit var  binding : ActivityMainBinding
    private lateinit var viewMovel: MainActivityViewModel
    lateinit var moviePageListRepository: MoviePageListRepository



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val apiService = MovieClient.getClient()

        moviePageListRepository = MoviePageListRepository(apiService)

        viewMovel = getViewModel()

        val posterAdapter = MovieHorizontalSlideAdapter(this)
        binding.horizontalRecyclerView.adapter = posterAdapter

        val movieAdapter = PopulatMoviePageListAdapter(this)
        val gridLayoutManager = GridLayoutManager(this, 3)
        binding.gridRecycleView.layoutManager = gridLayoutManager
        binding.gridRecycleView.adapter = movieAdapter
        binding.gridRecycleView.setHasFixedSize(true)

        viewMovel.moviePagedList.observe(this ,  {
            movieAdapter.submitList(it)
        })
        viewMovel.moviePagedList.observe(this ,  {
            posterAdapter.submitList(it)
        })


    }

    private fun getViewModel(): MainActivityViewModel {
        return ViewModelProvider(this , object : ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return MainActivityViewModel(moviePageListRepository) as T

            }
        })[MainActivityViewModel::class.java]
    }
}