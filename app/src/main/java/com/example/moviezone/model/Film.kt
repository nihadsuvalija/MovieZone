package com.example.moviezone.model


import com.google.gson.annotations.SerializedName

data class Film(
    @SerializedName("age_rating")
    val ageRating: List<AgeRating>,
    @SerializedName("film_id")
    val filmId: Int,
    @SerializedName("film_name")
    val filmName: String,
    @SerializedName("film_trailer")
    val filmTrailer: String,
    @SerializedName("images")
    val images: Images,
    @SerializedName("imdb_id")
    val imdbId: Int,
    @SerializedName("imdb_title_id")
    val imdbTitleId: String,
    @SerializedName("other_titles")
    val otherTitles: Any,
    @SerializedName("release_dates")
    val releaseDates: List<ReleaseDate>,
    @SerializedName("synopsis_long")
    val synopsisLong: String
)