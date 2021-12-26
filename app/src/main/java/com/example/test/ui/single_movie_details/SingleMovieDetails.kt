package com.example.test.ui.single_movie_details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.test.data.api.MovieClient
import com.example.test.data.api.MovieInterface
import com.example.test.data.api.POSTER_BASE_URL
import com.example.test.databinding.ActivityMovieDetailsBinding
import com.example.test.model.MovieModel

class SingleMovieDetails : AppCompatActivity() {

    private lateinit var viewModel: SingleMovieViewModel
    private lateinit var movieRepository: MovieDetailsRepository
    private lateinit var binding: ActivityMovieDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.backToolbar.backButton.setOnClickListener{ finish()}




        val movieId: Int = intent.getIntExtra("id" , 500)
        Log.e("MovieDetails" , "$movieId")

        val apiService: MovieInterface = MovieClient.getClient()
        movieRepository = MovieDetailsRepository(apiService)

        viewModel = getViewModel(movieId)

        viewModel.movieDetails.observe(this ,  {
            Log.e("Testing response" , "$it")
            bindUI(it)
        })


    }

    private fun bindUI(it : MovieModel){
        binding.movieTitleView.text = it.title
        binding.releaseDateView.text =it.releaseDate
        binding.ratingView.text = it.rating.toString()
        binding.overviewTextView.text = it.overview


        val moviePosterURL = POSTER_BASE_URL + it.posterPath
        Glide.with(this)
            .load(moviePosterURL)
            .into(binding.moviePoster)


    }

    private fun getViewModel(movieId:Int): SingleMovieViewModel {
        return ViewModelProvider(this , object : ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return SingleMovieViewModel(movieRepository , movieId) as T

            }
        })[SingleMovieViewModel::class.java]
    }
}