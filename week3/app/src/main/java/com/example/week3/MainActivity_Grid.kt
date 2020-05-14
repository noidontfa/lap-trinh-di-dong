package com.example.week3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(my_toolbar)
        val adapter = MovieAdapter(ctx = this, movies = MovieModel.parseToObject() )
        val Count: Int = 3
        val layoutManager = GridLayoutManager(this, Count)
        rv.layoutManager = layoutManager
        rv.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.my_menu, menu)
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean{

        var itemview = item.itemId
        when(itemview)
        {
            R.id.button_grid ->Toast.makeText(applicationContext, "grid view",Toast.LENGTH_SHORT).show()
            R.id.button_list ->Toast.makeText(applicationContext, "list view",Toast.LENGTH_SHORT).show()
        }
        return false
    }

}
