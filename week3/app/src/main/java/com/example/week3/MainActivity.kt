package com.example.week3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(my_toolbar)
        val adapter = MovieAdapter(ctx = this, movies = MovieModel.parseToObject() )
        val layoutManager = LinearLayoutManager(this)
        rv.layoutManager = layoutManager
        rv.adapter = adapter
        adapter.listener = object: MovieAdapter.MovieListener{
            override fun onClickListener(movie: MovieModel.Content) {
                //Toast.makeText(this@MainActivity, movie.title, Toast.LENGTH_SHORT).show()
                startDetailScreen(movie)
            }
        }
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

    private fun startDetailScreen(movie: MovieModel.Content) {
        val intent: Intent = Intent(this@MainActivity, DetailActivity::class.java)
        intent.putExtra(MOVIE_TITLE_KEY, movie.title)
        intent.putExtra(MOVIE_DESCRIPTION_KEY, movie.overview)
        intent.putExtra(MOVIE_IMAGE_POSTER_KEY, movie.backdrop_path)
        startActivity(intent)
    }

}
