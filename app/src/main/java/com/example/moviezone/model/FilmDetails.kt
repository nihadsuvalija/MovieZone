package com.example.moviezone.model


import com.google.gson.annotations.SerializedName

data class FilmDetails(
    @SerializedName("cast")
    val cast: List<Cast>,
    @SerializedName("directors")
    val directors: List<Director>,
    @SerializedName("distributor")
    val distributor: String,
    @SerializedName("distributor_id")
    val distributorId: Int,
    @SerializedName("duration_mins")
    val durationMins: Int,
    @SerializedName("film_id")
    val filmId: Int,
    @SerializedName("film_name")
    val filmName: String,
    @SerializedName("genres")
    val genres: List<Genre>,
    @SerializedName("images")
    val images: ImagesX,
    @SerializedName("imdb_id")
    val imdbId: Int,
    @SerializedName("imdb_title_id")
    val imdbTitleId: String,
    @SerializedName("other_titles")
    val otherTitles: Any,
    @SerializedName("producers")
    val producers: List<Producer>,
    @SerializedName("release_dates")
    val releaseDates: List<ReleaseDateX>,
    @SerializedName("review_stars")
    val reviewStars: Double,
    @SerializedName("show_dates")
    val showDates: List<ShowDate>,
    @SerializedName("synopsis_long")
    val synopsisLong: String,
    @SerializedName("trailers")
    val trailers: Trailers,
    @SerializedName("version_type")
    val versionType: String,
    @SerializedName("writers")
    val writers: List<Writer>
)