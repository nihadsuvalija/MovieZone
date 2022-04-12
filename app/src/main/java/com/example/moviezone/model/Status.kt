package com.example.moviezone.model


import com.google.gson.annotations.SerializedName

data class Status(
    @SerializedName("count")
    val count: Int,
    @SerializedName("device_datetime_sent")
    val deviceDatetimeSent: String,
    @SerializedName("device_datetime_used")
    val deviceDatetimeUsed: String,
    @SerializedName("message")
    val message: Any,
    @SerializedName("method")
    val method: String,
    @SerializedName("request_method")
    val requestMethod: String,
    @SerializedName("state")
    val state: String,
    @SerializedName("territory")
    val territory: String,
    @SerializedName("version")
    val version: String
)