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

class PopularMoviesAdapter: RecyclerView.Adapter<PopularMoviesAdapter.ViewHolder>() {

    private lateinit var binding: MovieItemBinding
    private lateinit var viewModel: HomeViewModel
    private var popularMovies: List<Movie> = listOf()

    fun setPopularMovies(popularMovies: List<Movie>) {
        var diffUtil = MoviesDiffUtil(this.popularMovies, popularMovies)
        var diffResult = DiffUtil.calculateDiff(diffUtil)
        this.popularMovies = popularMovies
        diffResult.dispatchUpdatesTo(this)
    }

    class ViewHolder(binding: MovieItemBinding): RecyclerView.ViewHolder(binding.root) {
        val moviePoster = binding.ivMoviePosterMovieitem
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.movie_item, parent, false)
        viewModel = ViewModelProvider(parent.findFragment())[HomeViewModel::class.java]

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(Const.TMDB_IMAGE_URL + popularMovies[position].posterPath)
            .into(holder.moviePoster)

        holder.itemView.setOnClickListener {
            popularMovies[position].id?.let { it1 -> viewModel.onMovieClick(it1) }
        }
    }

    override fun getItemCount(): Int {
        return popularMovies.size
    }

}