package com.example.moviezone.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.moviezone.model.SearchedMovie

class SearchedMovieDiffUtil(
    private val oldList: List<SearchedMovie>,
    private val newList: List<SearchedMovie>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}