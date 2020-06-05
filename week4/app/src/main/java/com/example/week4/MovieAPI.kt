package com.example.week4

import retrofit2.Call
import retrofit2.http.GET

interface MovieAPI {
    @GET("movie/top_rated")
    fun getTopRateMovie(): Call<VideoResponse>

}