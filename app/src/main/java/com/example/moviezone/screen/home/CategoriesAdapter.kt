package com.example.moviezone.screen.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviezone.R
import com.example.moviezone.databinding.CategoryItemBinding

class CategoriesAdapter: RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    private lateinit var binding: CategoryItemBinding
    private var categories: List<String> = listOf()

    fun setCategories(categories: List<String>) {
        this.categories = categories
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
    }

    override fun getItemCount(): Int {
        return categories.size
    }
}