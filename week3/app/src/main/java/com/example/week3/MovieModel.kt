package com.example.week3

import com.google.gson.Gson

class MovieModel {

    data class Result( val results : ArrayList<Content>,
                       val page : Int,
                       val total_results: Int,
                       val dates : Dates,
                       val total_pages : Int)

    data class Content(val id : Int,
                       val popularity : Float,
                       val vote_count : Int,
                       val poster_path: String,
                       val video : Boolean,
                       val adult: Boolean,
                       val backdrop_path : String,
                       val original_language : String,
                       val original_title : String,
                       val genre_ids : ArrayList<Int>,
                       val title : String,
                       val vote_average : Float,
                       val overview : String,
                       val release_date : String
    )
    data class Dates( val maximum : String,
                      val minimum : String)

    companion object {
        fun parseToObject() : Result {
            return Gson().fromJson(DataCenter.getMovieJsonString(),Result::class.java)
        }
    }

}
