package com.example.moviezone.model


import com.google.gson.annotations.SerializedName

data class Producer(
    @SerializedName("producer_id")
    val producerId: Int,
    @SerializedName("producer_name")
    val producerName: String
)