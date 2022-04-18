package com.example.moviezone.screen.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.findFragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.moviezone.R
import com.example.moviezone.databinding.CategoryItemBinding

class CategoriesAdapter: RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    private lateinit var binding: CategoryItemBinding
    private var categories: List<String> = listOf()
    private var viewModelInteractor: HomeViewModelInteractor? = null

    fun setCategories(categories: List<String>) {
        this.categories = categories
    }

    fun setViewModelInteractor(viewModelInteractor: HomeViewModelInteractor) {
        this.viewModelInteractor = viewModelInteractor
    }

    class ViewHolder(binding: CategoryItemBinding): RecyclerView.ViewHolder(binding.root) {
        var title = binding.tvCategoryTitleCategoryitem
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoriesAdapter.ViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.category_item, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoriesAdapter.ViewHolder, position: Int) {
        holder.title.text = categories[position]
        holder.itemView.setOnClickListener {
            when (categories[position]) {
                "Now Playing" -> {
                    viewModelInteractor?.showNowPlayingMovies()
                }
                "Upcoming" -> {
                    viewModelInteractor?.showUpcomingMovies()
                }
                "Favorites" -> {
                    viewModelInteractor?.showFavoriteMovies()
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return categories.size
    }
}