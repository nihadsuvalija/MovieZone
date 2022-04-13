package com.example.moviezone.model


import com.google.gson.annotations.SerializedName

data class Writer(
    @SerializedName("writer_id")
    val writerId: Int,
    @SerializedName("writer_name")
    val writerName: String
)