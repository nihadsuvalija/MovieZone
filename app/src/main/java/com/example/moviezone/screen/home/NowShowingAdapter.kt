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
import com.example.moviezone.model.Film
import com.example.moviezone.utils.FilmsDiffUtil

class NowShowingAdapter: RecyclerView.Adapter<NowShowingAdapter.ViewHolder>() {

    private lateinit var binding: MovieItemBinding
    private lateinit var viewModel: HomeViewModel

    private var films: List<Film> = listOf()

    fun setNowShowing(films: List<Film>) {
        val diffUtil = FilmsDiffUtil(this.films, films)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        this.films = films
        diffResult.dispatchUpdatesTo(this)
    }

    class ViewHolder(binding: MovieItemBinding): RecyclerView.ViewHolder(binding.root) {
        val poster = binding.ivMoviePosterMovieitem
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NowShowingAdapter.ViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.movie_item, parent, false)
        viewModel = ViewModelProvider(parent.findFragment())[HomeViewModel::class.java]

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NowShowingAdapter.ViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(films[position].images.poster.x1.medium.filmImage)
            .into(holder.poster)

        println(films[position].images.poster.x1.medium.filmImage)
        // Add on movie click display details of movie

        holder.itemView.setOnClickListener {
            viewModel.onFilmClick(films[position].filmId)
        }
    }

    override fun getItemCount(): Int {
        return films.size
    }
}