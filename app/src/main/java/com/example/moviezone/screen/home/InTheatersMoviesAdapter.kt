package com.example.moviezone.screen.home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.findFragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviezone.R
import com.example.moviezone.databinding.MovieItemBinding
import com.example.moviezone.imdbmodel.Movie
import com.example.moviezone.utils.IMDBMoviesDiffUtil
import com.example.moviezone.utils.MoviesDiffUtil

class InTheatersMoviesAdapter: RecyclerView.Adapter<InTheatersMoviesAdapter.ViewHolder>() {

    private lateinit var binding: MovieItemBinding
    private lateinit var viewModel: HomeViewModel

    private var movies: List<Movie> = listOf()

    fun setInTheatersMovies(movies: List<Movie>) {
        val diffUtil = IMDBMoviesDiffUtil(this.movies, movies)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        this.movies = movies
        diffResult.dispatchUpdatesTo(this)
    }

    class ViewHolder(binding: MovieItemBinding): RecyclerView.ViewHolder(binding.root) {
        val poster = binding.ivMoviePosterMovieitem
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): InTheatersMoviesAdapter.ViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.movie_item, parent, false)
        viewModel = ViewModelProvider(parent.findFragment())[HomeViewModel::class.java]

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InTheatersMoviesAdapter.ViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(movies[position].image)
            .into(holder.poster)

        holder.itemView.setOnClickListener {
            viewModel.onMovieClick(movies[position].id)
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}