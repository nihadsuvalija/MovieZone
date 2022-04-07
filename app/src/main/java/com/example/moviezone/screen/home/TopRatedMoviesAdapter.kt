package com.example.moviezone.screen.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviezone.R
import com.example.moviezone.databinding.MovieItemBinding
import com.example.moviezone.model.Movie
import com.example.moviezone.utils.Const
import com.example.moviezone.utils.MoviesDiffUtil

class TopRatedMoviesAdapter: RecyclerView.Adapter<TopRatedMoviesAdapter.TopRatedMoviesViewHolder>() {

    private var topRatedMovies: List<Movie> = listOf()
    private lateinit var binding: MovieItemBinding

    fun setTopRatedMovies(topRatedMovies: List<Movie>) {
        var diffUtil = MoviesDiffUtil(this.topRatedMovies, topRatedMovies)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        this.topRatedMovies = topRatedMovies
        diffResult.dispatchUpdatesTo(this)
    }

    class TopRatedMoviesViewHolder(binding: MovieItemBinding): RecyclerView.ViewHolder(binding.root) {
        val moviePoster = binding.ivMoviePosterMovieitem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatedMoviesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = DataBindingUtil.inflate(inflater, R.layout.movie_item, parent, false)

        return TopRatedMoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TopRatedMoviesViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(Const.TMDB_IMAGE_URL + topRatedMovies[position].posterPath)
            .into(holder.moviePoster)
    }

    override fun getItemCount(): Int {
        return topRatedMovies.size
    }
}