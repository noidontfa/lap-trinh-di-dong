package com.example.week4

import okhttp3.Interceptor
import okhttp3.Response

//<<<<<<< HEAD
//class AuthInterceptor: Interceptor {
//    override fun intercept(chain: Interceptor.Chain): Response {
//        val request = chain.request()
//        val url = request.url().newBuilder()
//            .addQueryParameter("api_key", MainActivity.API_KEY).build()
//        val finalRequest = request.newBuilder().url(url).build()
//        return chain.proceed(finalRequest)
//    }
//
//=======
class AuthInterceptor : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url.newBuilder().addQueryParameter("api_key", MainActivity.API_KEY).build()
        val finalRequest = request.newBuilder().url(url).build()
        return chain.proceed(finalRequest)
    }
//>>>>>>> 157adf268c5125a9aa63d5fae3cf7e0f6cdeb3a6
}