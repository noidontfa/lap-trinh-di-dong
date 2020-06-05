package com.example.week4

import com.google.gson.annotations.SerializedName

data class VideoResponse(
    val page: Int?,
    @SerializedName("total_results") val totalResults: Int?,
    @SerializedName("total_pages") val totalPages: Int?,
    @SerializedName("results") val result: ArrayList<Video>?
){
    constructor() : this(null,null,null, ArrayList())
}