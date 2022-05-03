package com.example.moviezone.screen.moviedetails

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.moviezone.R
import com.example.moviezone.databinding.MovieItemBinding
import com.example.moviezone.model.Movie
import com.example.moviezone.utils.Const
import com.example.moviezone.utils.MovieDiffUtil

class SimilarMoviesAdapter: RecyclerView.Adapter<SimilarMoviesAdapter.ViewHolder>() {

    private var movies: List<Movie> = listOf()
    private lateinit var binding: MovieItemBinding
    private var viewModelInteractor: MovieDetailsViewModelInteractor? = null

    fun setMovies(movies: List<Movie>) {
        val diffUtil = MovieDiffUtil(this.movies, movies)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        this.movies = movies
        diffResult.dispatchUpdatesTo(this)
    }

    fun setViewModelInteractor(viewModelInteractor: MovieDetailsViewModelInteractor?) {
        this.viewModelInteractor = viewModelInteractor
    }

    class ViewHolder(binding: MovieItemBinding): RecyclerView.ViewHolder(binding.root) {
        val poster = binding.ivMoviePosterMovieitem
        val rating = binding.tvRatingMovieitem
        val ratingStar = binding.ivRatingMovieitem
        val ratingBackground = binding.vRatingBackgroundMovieitem
        val progressBar = binding.progressBarMovieitem
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SimilarMoviesAdapter.ViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.movie_item, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SimilarMoviesAdapter.ViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(Const.TMDB_IMAGE_URL + movies[position].posterPath)
            .listener(object: RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    holder.poster.visibility = View.GONE
                    holder.rating.visibility = View.GONE
                    holder.ratingStar.visibility = View.GONE
                    holder.ratingBackground.visibility = View.GONE
                    holder.progressBar.visibility = View.VISIBLE
                    holder.itemView.isClickable = false
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    holder.poster.visibility = View.VISIBLE
                    holder.rating.visibility = View.VISIBLE
                    holder.ratingStar.visibility = View.VISIBLE
                    holder.ratingBackground.visibility = View.VISIBLE
                    holder.progressBar.visibility = View.GONE
                    holder.itemView.isClickable = true
                    return false
                }

            })
            .into(holder.poster)

        holder.rating.text = movies[position].voteAverage.toString()

        holder.itemView.setOnClickListener {
            viewModelInteractor?.showMovieDetails(movies[position].id)
        }

        holder.rating.text = String.format("%.1f", movies[position].voteAverage)
    }

    override fun getItemCount(): Int {
        return movies.size;
    }

}
