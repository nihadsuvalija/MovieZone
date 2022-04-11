package com.example.moviezone.screen.moviedetails

import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviezone.R
import com.example.moviezone.databinding.ReviewBinding
import com.example.moviezone.model.Review
import com.example.moviezone.utils.Const
import com.example.moviezone.utils.ReviewDiffUtil
import java.text.SimpleDateFormat

class ReviewAdapter: RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {

    private lateinit var binding: ReviewBinding
    private var reviews: List<Review> = listOf()

    fun setReviews(reviews: List<Review>) {
        val diffUtil = ReviewDiffUtil(this.reviews, reviews)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        this.reviews = reviews
        diffResult.dispatchUpdatesTo(this)
    }

    class ReviewViewHolder(binding: ReviewBinding): RecyclerView.ViewHolder(binding.root) {
        val author = binding.tvAuthorReview
        val content = binding.tvContentReview
        var createdAt = binding.tvCreatedAtReview
        var rating = binding.tvRatingReview
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReviewViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.review, parent, false)

        return ReviewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        var author = reviews[position].author
        if (author.length >= Const.MAX_TEXT_VIEW_LENGTH) {
            author = author.subSequence(0, Const.MAX_TEXT_VIEW_LENGTH - 3).toString()
            author += "..."
        }
        holder.author.text = author

        // content has HTML in it we don't want, so we remove the tags and any html related characters
        holder.content.text = reviews[position].content.replace("\\<[^>]*>","")

        // Example date returned from API: 2020-07-09T14:46:230
        val date = reviews[position].createdAt.subSequence(0, 10)

        holder.rating.text = reviews[position].authorDetails.rating.toString()
        holder.createdAt.text = date
    }

    override fun getItemCount(): Int {
        return reviews.size
    }

}