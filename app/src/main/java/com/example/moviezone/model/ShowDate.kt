package com.example.moviezone.model


import com.google.gson.annotations.SerializedName

data class ShowDate(
    @SerializedName("date")
    val date: String
)