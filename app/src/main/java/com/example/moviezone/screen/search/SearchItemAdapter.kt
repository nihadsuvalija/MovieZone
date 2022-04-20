package com.example.moviezone.screen.search

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviezone.R
import com.example.moviezone.databinding.SearchItemBinding
import com.example.moviezone.model.SearchedMovie
import com.example.moviezone.screen.home.HomeViewModel
import com.example.moviezone.screen.home.HomeViewModelInteractor
import com.example.moviezone.utils.Const
import com.example.moviezone.utils.SearchedMovieDiffUtil

class SearchItemAdapter: RecyclerView.Adapter<SearchItemAdapter.ViewHolder>() {

    private lateinit var binding: SearchItemBinding
    private var movies: List<SearchedMovie> = listOf()

    private var viewModelInteractor: SearchViewModelInteractor? = null

    fun setViewModelInteractor(viewModelInteractor: SearchViewModelInteractor?) {
        this.viewModelInteractor = viewModelInteractor
    }

    fun setMovies(movies: List<SearchedMovie>) {
        val diffUtil = SearchedMovieDiffUtil(this.movies, movies)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        this.movies = movies
        diffResult.dispatchUpdatesTo(this)
    }

    class ViewHolder(binding: SearchItemBinding): RecyclerView.ViewHolder(binding.root) {
        val poster = binding.ivPosterSearchitem
        val title = binding.tvTitleSearchitem
        val releaseDate = binding.tvReleaseDateSearchitem
        val runtime = binding.tvRuntimeSearchitem
        val genre = binding.tvGenreSearchitem
        val rating = binding.tvRatingSearchitem
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchItemAdapter.ViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.search_item, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchItemAdapter.ViewHolder, position: Int) {
        if (movies[position].title != "") {
            try {
                if (movies[position].posterPath.isEmpty()) {
                    holder.poster.visibility = View.GONE
                } else {
                    Glide.with(holder.itemView.context)
                        .load(Const.TMDB_IMAGE_URL + movies[position].posterPath)
                        .into(holder.poster)
                }

                if (movies[position].title.length > 10) {
                    val newTitle = movies[position].title.subSequence(0, 8).toString() + "..."
                    holder.title.text = newTitle
                } else {
                    holder.title.text = movies[position].title
                }

                holder.releaseDate.text = movies[position].releaseDate
                holder.rating.text = movies[position].voteAverage.toString()
            } catch (e: Exception) {
                println(e.message)
            }

            holder.itemView.setOnClickListener {
                viewModelInteractor?.showMovieDetails(movies[position].id, Const.SEARCH_PAGE_INDEX)
            }
        }

    }

    override fun getItemCount(): Int {
        return movies.size
    }
}