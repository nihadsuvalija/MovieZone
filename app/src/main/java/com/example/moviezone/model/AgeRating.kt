package com.example.moviezone.model


import com.google.gson.annotations.SerializedName

data class AgeRating(
    @SerializedName("age_advisory")
    val ageAdvisory: String,
    @SerializedName("age_rating_image")
    val ageRatingImage: String,
    @SerializedName("rating")
    val rating: String
)