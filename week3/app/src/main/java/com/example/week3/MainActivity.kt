package com.example.week3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(my_toolbar)

        val adapter = MovieAdapter(ctx = this, movies = MovieModel.parseToObject() , type = 0)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter
        adapter.listener = object: MovieAdapter.MovieListener{
            override fun onClickListener(movie: MovieModel.Content) {
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
            R.id.button_grid -> {
                val Count: Int = 3
                val adapter = MovieAdapter(ctx = this, movies = MovieModel.parseToObject() , type = 1)
                rv.adapter = adapter
                rv.layoutManager = GridLayoutManager(this, Count)
                adapter.listener = object: MovieAdapter.MovieListener{
                    override fun onClickListener(movie: MovieModel.Content) {
                        startDetailScreen(movie)
                    }
                }
            }

            R.id.button_list -> {
                val adapter = MovieAdapter(ctx = this, movies = MovieModel.parseToObject() , type = 0)
                rv.adapter = adapter
                rv.layoutManager = LinearLayoutManager(this)
                adapter.listener = object: MovieAdapter.MovieListener{
                    override fun onClickListener(movie: MovieModel.Content) {
                        startDetailScreen(movie)
                    }
                }
            }
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
