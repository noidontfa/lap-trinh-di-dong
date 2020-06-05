package com.example.week4

import com.google.gson.annotations.SerializedName

data class VideoResponse(
    val page: Int,
    @SerializedName("total_results") val total_results: Int,
    @SerializedName("total_pages") val total_pages: Int,
    @SerializedName("results") val results: ArrayList<Video>
)