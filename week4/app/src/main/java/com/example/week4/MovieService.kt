package com.example.week4

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieService {
    private var api: MovieAPI
    init {
        api = createInstance()
    }

    companion object{
        private var mInstance : MovieService? = null

        fun getInstance() = mInstance ?: synchronized(this){
            mInstance ?: MovieService().also { mInstance = it }
        }
    }

    private fun createInstance() : MovieAPI{
        val okHttpClient = OkHttpClient.Builder().addInterceptor(AuthInterceptor())
            .build()

        val gson = GsonBuilder().setLenient().create()

        val retrofit = Retrofit.Builder()
            .baseUrl(MainActivity.URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
        return retrofit.create(MovieAPI::class.java)
    }

    fun getApi() : MovieAPI = api
}