package com.example.week4

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.io.FileDescriptor

@Parcelize
@Entity
data class Movie(
    @PrimaryKey(autoGenerate = true) var id : Int?=null,
    var name:String,
    var description : String,
    var movie_poster_path : String
): Parcelable {
    constructor(): this(null, "","", "")
}


