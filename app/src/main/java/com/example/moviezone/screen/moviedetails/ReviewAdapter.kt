package com.example.moviezone.screen.moviedetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviezone.R
import com.example.moviezone.databinding.ReviewBinding
import com.example.moviezone.model.Review
import com.example.moviezone.utils.ReviewDiffUtil

class ReviewAdapter: RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {

    private lateinit var binding: ReviewBinding

    private var reviews: List<Review> = listOf()

    fun setReviews(reviews: List<Review>) {
        val diffUtil = ReviewDiffUtil(this.reviews, reviews)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        this.reviews = reviews
        diffResult.dispatchUpdatesTo(this)
    }

    class ViewHolder(binding: ReviewBinding): RecyclerView.ViewHolder(binding.root) {
        val author = binding.tvAuthorReview
        val content = binding.tvContentReview
        val rating = binding.tvRatingReview
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewAdapter.ViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.review, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReviewAdapter.ViewHolder, position: Int) {
        holder.author.text = reviews[position].author
        holder.content.text = reviews[position].content
        holder.rating.text = reviews[position].authorDetails.rating.toString()
    }

    override fun getItemCount(): Int {
        return reviews.size
    }
}