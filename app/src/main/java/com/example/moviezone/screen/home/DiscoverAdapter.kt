package com.example.moviezone.screen.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviezone.R
import com.example.moviezone.databinding.DiscoverItemBinding
import com.example.moviezone.model.Movie
import com.example.moviezone.utils.Const
import com.example.moviezone.utils.MovieDiffUtil

class DiscoverAdapter: RecyclerView.Adapter<DiscoverAdapter.ViewHolder>() {

    private lateinit var binding: DiscoverItemBinding
    private var movies: List<Movie> = listOf()
    private var viewModelInteractor: HomeViewModelInteractor? = null

    fun setMovies(movies: List<Movie>) {
        val diffUtil = MovieDiffUtil(this.movies, movies)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        this.movies = movies
        diffResult.dispatchUpdatesTo(this)
    }

    fun setViewModelInteractor(viewModelInteractor: HomeViewModelInteractor?) {
        this.viewModelInteractor = viewModelInteractor
    }

    class ViewHolder(binding: DiscoverItemBinding): RecyclerView.ViewHolder(binding.root) {
        val backdrop = binding.ivBackdropDiscoveritem
        val title = binding.tvTitleDiscoveritem
        val rating = binding.tvRatingDiscoveritem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscoverAdapter.ViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.discover_item, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DiscoverAdapter.ViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(Const.TMDB_IMAGE_URL + movies[position].backdropPath)
            .into(holder.backdrop)

        var title = movies[position].title
        if (title.length > 18) {
            title = title.subSequence(0,15).toString() + "..."
        }
        holder.title.text = title

        holder.itemView.setOnClickListener {
            viewModelInteractor?.showMovieDetails(movies[position].id, Const.HOME_PAGE_INDEX)
        }

        holder.rating.text = movies[position].voteAverage.toString()
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}