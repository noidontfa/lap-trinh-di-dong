package com.example.week4

import com.google.gson.annotations.SerializedName

data class Video(
    val adult: Boolean?,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("genre_ids") val genreIds: List<Int>?,
    var id: Int?,
    @SerializedName("original_language") val originalLanguage: String?,
    @SerializedName("original_title") val originalTitle: String?,
    var overview: String?,
    val popularity: Double?,
    @SerializedName("poster_path") var poster_path: String?,
    @SerializedName("release_date") val releaseDate: String?,
    var title: String?,
    val video: Boolean?,
    @SerializedName("vote_average") val voteAverage: Double?,
    @SerializedName("vote_count") val voteCount: Int?
){
    constructor() : this(null,null,null,null,null,null, null,null, null, null, null, null, null, null)
}