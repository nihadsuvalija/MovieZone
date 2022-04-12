package com.example.moviezone.model


import com.google.gson.annotations.SerializedName

data class Images(
    @SerializedName("poster")
    val poster: Poster,
    @SerializedName("still")
    val still: Still
)