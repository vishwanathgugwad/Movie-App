package com.example.test.ui.mainpagemovies

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.test.data.api.POSTER_BASE_URL
import com.example.test.databinding.ImageContainerHorizontalBinding
import com.example.test.model.Movie
import com.example.test.ui.single_movie_details.SingleMovieDetails

class MovieHorizontalSlideAdapter(val context: Context) :
    PagedListAdapter<Movie , RecyclerView.ViewHolder>(MovieDiffCallback()) {


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder , position: Int) {

        getItem(position)?.let { (holder as MovieItemViewHolder).bind(it , context) }


    }

    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int): RecyclerView.ViewHolder {

        val view = ImageContainerHorizontalBinding.inflate(
            LayoutInflater.from(parent.context) ,
            parent ,
            false
        )

        return MovieItemViewHolder(view)

    }


    class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie , newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie , newItem: Movie): Boolean {
            return oldItem == newItem
        }

    }






    class MovieItemViewHolder(val binding: ImageContainerHorizontalBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie , context: Context) {

            val moviePosterUrl = POSTER_BASE_URL + movie.posterPath
            Glide.with(context)
                .load(moviePosterUrl)
                .into(binding.horizontalImageView)

//
            binding.horizontalImageView.setOnClickListener {
                val intent =
                    Intent(context , SingleMovieDetails::class.java)
                intent.putExtra("id" , movie.id)
                context.startActivity(intent)
            }


        }

    }

}