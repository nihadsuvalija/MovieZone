package com.example.moviezone.imdbmodel


import com.google.gson.annotations.SerializedName

data class MovieDetails(
    @SerializedName("actorList")
    val actorList: List<Actor>,
    @SerializedName("awards")
    val awards: String,
    @SerializedName("boxOffice")
    val boxOffice: BoxOffice,
    @SerializedName("companies")
    val companies: String,
    @SerializedName("companyList")
    val companyList: List<Company>,
    @SerializedName("contentRating")
    val contentRating: String,
    @SerializedName("countries")
    val countries: String,
    @SerializedName("countryList")
    val countryList: List<Country>,
    @SerializedName("directorList")
    val directorList: List<DirectorX>,
    @SerializedName("directors")
    val directors: String,
    @SerializedName("errorMessage")
    val errorMessage: Any,
    @SerializedName("fullCast")
    val fullCast: Any,
    @SerializedName("fullTitle")
    val fullTitle: String,
    @SerializedName("genreList")
    val genreList: List<GenreX>,
    @SerializedName("genres")
    val genres: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("imDbRating")
    val imDbRating: String,
    @SerializedName("imDbRatingVotes")
    val imDbRatingVotes: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("images")
    val images: Any,
    @SerializedName("keywordList")
    val keywordList: List<String>,
    @SerializedName("keywords")
    val keywords: String,
    @SerializedName("languageList")
    val languageList: List<Language>,
    @SerializedName("languages")
    val languages: String,
    @SerializedName("metacriticRating")
    val metacriticRating: String,
    @SerializedName("originalTitle")
    val originalTitle: String,
    @SerializedName("plot")
    val plot: String,
    @SerializedName("plotLocal")
    val plotLocal: String,
    @SerializedName("plotLocalIsRtl")
    val plotLocalIsRtl: Boolean,
    @SerializedName("posters")
    val posters: Any,
    @SerializedName("ratings")
    val ratings: Any,
    @SerializedName("releaseDate")
    val releaseDate: String,
    @SerializedName("runtimeMins")
    val runtimeMins: String,
    @SerializedName("runtimeStr")
    val runtimeStr: String,
    @SerializedName("similars")
    val similars: List<Similar>,
    @SerializedName("starList")
    val starList: List<StarX>,
    @SerializedName("stars")
    val stars: String,
    @SerializedName("tagline")
    val tagline: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("trailer")
    val trailer: Any,
    @SerializedName("tvEpisodeInfo")
    val tvEpisodeInfo: Any,
    @SerializedName("tvSeriesInfo")
    val tvSeriesInfo: Any,
    @SerializedName("type")
    val type: String,
    @SerializedName("wikipedia")
    val wikipedia: Any,
    @SerializedName("writerList")
    val writerList: List<Writer>,
    @SerializedName("writers")
    val writers: String,
    @SerializedName("year")
    val year: String
)