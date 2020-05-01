package com.example.myapplication1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_connexion.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.create_an_account.*
import kotlinx.android.synthetic.main.intro_screen.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.intro_screen)
        buttonStart.setOnClickListener {
            val intent = Intent(this,ConnexionActivity::class.java)
            startActivity(intent)
        }


    }

}
