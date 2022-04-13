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
    private lateinit var viewModel: HomeViewModel

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
        viewModel = ViewModelProvider(parent.findFragment())[HomeViewModel::class.java]

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoriesAdapter.ViewHolder, position: Int) {
        holder.title.text = categories[position]
        holder.itemView.setOnClickListener {
            when (categories[position]) {
                "Now Showing" -> {
                    viewModel.getNowShowing()
                }
                "Coming Soon" -> {
                    viewModel.getComingSoon()
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return categories.size
    }
}