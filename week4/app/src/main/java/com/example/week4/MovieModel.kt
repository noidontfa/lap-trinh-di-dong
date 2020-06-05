package com.example.week4

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val MOVIE_TITLE_KEY = "MOVIE_TITLE_KEY"
const val MOVIE_DESCRIPTION_KEY = "MOVIE_DESCRIPTION_KEY"
const val MOVIE_IMAGE_POSTER_KEY = "MOVIE_IMAGE_POSTER_KEY"

class MovieModel {

//    data class Result( val results : ArrayList<Content>?,
//                       val page : Int?,
//                       val total_results: Int?,
//                       val dates : Dates?,
//                       val total_pages : Int?) {
//        constructor() : this(ArrayList(),null,null,null,null)
//    }
//
//    data class Content(
//        var id : Int?,
//        val popularity : Float?,
//        val vote_count : Int?,
//        var poster_path: String?,
//        val video : Boolean?,
//        val adult: Boolean?,
//        val backdrop_path : String?,
//        val original_language : String?,
//        val original_title : String?,
//        val genre_ids : ArrayList<Int>?,
//        var title : String?,
//        val vote_average : Float?,
//        var overview : String?,
//        val release_date : String?
//    ) {
//        constructor() : this(null,null,null,null,null,null,
//            null,null, null, null, null, null, null, null)
//    }
//
//    data class Dates( val maximum : String,
//                      val minimum : String)

    companion object {
//        fun getTopRateMovieObject(): Result {
//            return Gson().fromJson(DataCenter.getTopRateMovieJson(),Result::class.java)
//        }
        var resp = VideoResponse()
        var resp2 = VideoResponse()

        fun getDataFromApi(): VideoResponse {

                MovieService.getInstance().getApi().getTopRateMovie().enqueue(object :
                    Callback<VideoResponse> {
                    override fun onFailure(call: Call<VideoResponse>?, t: Throwable?) {
                        //todo something
                        Log.e("MainActivity", "Problem calling Github API {${t?.message}}")
                    }

                    override fun onResponse(
                        call: Call<VideoResponse>?,
                        response: Response<VideoResponse>?
                    ) {
                        response?.let {
                            resp = it.body()
                            Log.e(MainActivity.TAG, "ok ok ok {${resp.result}}")
                        }
                    }
                })
                Log.e(MainActivity.TAG, "end{${resp.page}}")
            return resp
        }

        fun getDataFromApiForNowPlaying(): VideoResponse {

            MovieService.getInstance().getApi().getNowPlaying().enqueue(object :
                Callback<VideoResponse> {
                override fun onFailure(call: Call<VideoResponse>?, t: Throwable?) {
                    //todo something
                    Log.e("MainActivity", "Problem calling Github API {${t?.message}}")
                }

                override fun onResponse(
                    call: Call<VideoResponse>?,
                    response: Response<VideoResponse>?
                ) {
                    response?.let {
                        resp2 = it.body()
                        Log.e(MainActivity.TAG, "ok ok ok {${resp2.result}}")
                    }
                }
            })
            Log.e(MainActivity.TAG, "end{${resp2.page}}")
            return resp2
        }

//        fun getNowPlayingMovieObject(): Result {
//            return Gson().fromJson(DataCenter.getNowPlayingMovieJson(),Result::class.java)
//        }

    }

}
