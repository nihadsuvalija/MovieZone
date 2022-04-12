package com.example.moviezone.screen.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.findFragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviezone.R
import com.example.moviezone.databinding.MovieItemBinding
import com.example.moviezone.model.Movie
import com.example.moviezone.utils.Const
import com.example.moviezone.utils.MoviesDiffUtil

class LikedMoviesAdapter: RecyclerView.Adapter<LikedMoviesAdapter.LikedMoviesViewHolder>() {

    private lateinit var binding: MovieItemBinding
    private lateinit var viewModel: HomeViewModel

    private var movies: List<Movie> = listOf()

    fun setLikedMovies(movies: List<Movie>) {
        val diffUtil = MoviesDiffUtil(this.movies, movies)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        this.movies = movies
        diffResult.dispatchUpdatesTo(this)
    }

    class LikedMoviesViewHolder(binding: MovieItemBinding): RecyclerView.ViewHolder(binding.root) {
        val poster = binding.ivMoviePosterMovieitem
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LikedMoviesAdapter.LikedMoviesViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.movie_item, parent, false)
        viewModel = ViewModelProvider(parent.findFragment())[HomeViewModel::class.java]

        return LikedMoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LikedMoviesAdapter.LikedMoviesViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(Const.TMDB_IMAGE_URL + movies[position].posterPath)
            .into(holder.poster)
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}