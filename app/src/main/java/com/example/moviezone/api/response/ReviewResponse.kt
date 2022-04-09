package com.example.moviezone.api.response


import com.example.moviezone.model.Review
import com.google.gson.annotations.SerializedName

data class ReviewResponse(
    @SerializedName("results")
    val reviews: List<Review>
)