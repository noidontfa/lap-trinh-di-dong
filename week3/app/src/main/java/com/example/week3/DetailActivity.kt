package com.example.week3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RatingBar
import android.widget.Toast

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.hide()

        val rate = findViewById<View>(R.id.rating_bar) as RatingBar
        val review = findViewById<Button>(R.id.btn_review) as Button
        review.setOnClickListener(View.OnClickListener {
            Toast.makeText(this, "Your rating:" + rate.rating.toString(), Toast.LENGTH_LONG).show()
        })

        getAndDisplayData()
    }

    private fun getAndDisplayData() {
        val data : Bundle? = intent.extras
    }
} 
