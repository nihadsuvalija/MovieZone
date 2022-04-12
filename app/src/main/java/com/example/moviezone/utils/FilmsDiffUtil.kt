package com.example.moviezone.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.moviezone.model.Film

class FilmsDiffUtil(
    private val oldList: List<Film>,
    private val newList: List<Film>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].filmId == newList[newItemPosition].filmId
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}