package com.example.week4

import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val MOVIE_TITLE_KEY = "MOVIE_TITLE_KEY"
const val MOVIE_DESCRIPTION_KEY = "MOVIE_DESCRIPTION_KEY"
const val MOVIE_IMAGE_POSTER_KEY = "MOVIE_IMAGE_POSTER_KEY"

class MovieModel {

    data class Result( val results : ArrayList<Content>?,
                       val page : Int?,
                       val total_results: Int?,
                       val dates : Dates?,
                       val total_pages : Int?) {
        constructor() : this(ArrayList(),null,null,null,null)
    }

    data class Content(
        var id : Int?,
        val popularity : Float?,
        val vote_count : Int?,
        var poster_path: String?,
        val video : Boolean?,
        val adult: Boolean?,
        val backdrop_path : String?,
        val original_language : String?,
        val original_title : String?,
        val genre_ids : ArrayList<Int>?,
        var title : String?,
        val vote_average : Float?,
        var overview : String?,
        val release_date : String?
    ) {
        constructor() : this(null,null,null,null,null,null,
            null,null, null, null, null, null, null, null)
    }

    data class Dates( val maximum : String,
                      val minimum : String)

    companion object {
        fun getTopRateMovieObject(): Result {
            return Gson().fromJson(DataCenter.getTopRateMovieJson(),Result::class.java)
        }
         fun getNowPlayingMovieObject(): Result {
            return Gson().fromJson(DataCenter.getNowPlayingMovieJson(),Result::class.java)
        }
    }

}
