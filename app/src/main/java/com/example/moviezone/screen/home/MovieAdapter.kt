package com.example.moviezone.screen.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.findFragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviezone.R
import com.example.moviezone.databinding.MovieItemBinding
import com.example.moviezone.model.Movie
import com.example.moviezone.utils.Const
import com.example.moviezone.utils.MovieDiffUtil

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private lateinit var binding: MovieItemBinding
    private var viewModelInteractor: HomeViewModelInteractor? = null

    private var movies: List<Movie> = listOf()

    fun setViewModelInteractor(viewModelInteractor: HomeViewModelInteractor?) {
        this.viewModelInteractor = viewModelInteractor
    }

    fun setMovies(movies: List<Movie>) {
        val diffUtil = MovieDiffUtil(this.movies, movies)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        this.movies = movies
        diffResult.dispatchUpdatesTo(this)
    }

    class ViewHolder(binding: MovieItemBinding): RecyclerView.ViewHolder(binding.root) {
        val poster = binding.ivMoviePosterMovieitem
        val rating = binding.tvRatingMovieitem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.ViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.movie_item, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieAdapter.ViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(Const.TMDB_IMAGE_URL + movies[position].posterPath)
            .into(holder.poster)

        holder.rating.text = movies[position].voteAverage.toString()

        holder.itemView.setOnClickListener {
            viewModelInteractor?.showMovieDetails(movies[position].id, Const.HOME_PAGE_INDEX)
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}