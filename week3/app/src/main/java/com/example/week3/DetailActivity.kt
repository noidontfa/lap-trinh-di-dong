package com.example.week3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RatingBar
import android.widget.Toast
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.hide()

        ratingFilm()

        getAndDisplayData()
    }

    private fun ratingFilm() {
        val rate = findViewById<View>(R.id.rating_bar) as RatingBar
        val review = findViewById<Button>(R.id.btn_review) as Button
        review.setOnClickListener(View.OnClickListener {
            Toast.makeText(this, "Your rating:" + rate.rating.toString(), Toast.LENGTH_LONG).show()
        })
    }

    private fun getAndDisplayData() {
        val data : Bundle? = intent.extras

        if(data != null) {
            val nameFilm : String? = data.getString(MOVIE_TITLE_KEY)
            val description: String? = data.getString(MOVIE_DESCRIPTION_KEY)
            val imgPoster: String? = data.getString(MOVIE_IMAGE_POSTER_KEY)

            tvTitle.text = nameFilm
            tvDescription.text = description
            Glide.with(this)
                .load(imgPoster)
                .centerCrop()
                .placeholder(R.drawable.place_holder)
                .into(ivHeader)
        }
    }
} 
