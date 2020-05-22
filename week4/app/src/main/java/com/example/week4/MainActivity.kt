package com.example.week4


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_top_rating.*

class MainActivity : AppCompatActivity() {

    lateinit var topRatingFragment: TopRatingFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(my_toolbar)



//        val bottomNavigation : BottomNavigationView = findViewById(R.id.navigationView)

        topRatingFragment = TopRatingFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout, topRatingFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()


//        if(supportFragmentManager.backStackEntryCount == 0){
//            val frag_lv = FragmentListView()
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.container, frag_lv)
//                .commit()
//        }
//        adapter.listener = object: MovieAdapter.MovieListener{
//            override fun onClickListener(movie: MovieModel.Content) {
//                startDetailScreen(movie)
//            }
//        }
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
//                val Count: Int = 3
//                val adapter = MovieAdapter(ctx = this, movies = MovieModel.parseToObject() , type = 1)
//                rv.adapter = adapter
//                rv.layoutManager = GridLayoutManager(this, Count)

//                adapter.listener = object: MovieAdapter.MovieListener{
//                    override fun onClickListener(movie: MovieModel.Content) {
//                        startDetailScreen(movie)
//                    }
//                }
            }

            R.id.button_list -> {
                val adapter = MovieAdapter(ctx = this, movies = MovieModel.parseToObject() , type = 0)
//                rv.adapter = adapter
//                rv.layoutManager = LinearLayoutManager(this)
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
