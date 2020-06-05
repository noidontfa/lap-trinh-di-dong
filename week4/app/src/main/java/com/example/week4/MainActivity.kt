package com.example.week4


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_top_rating.*
import androidx.room.Room
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var topRatingFragment: TopRatingFragment
    lateinit var nowPlayingFragment: NowPlayingFragment
    lateinit var db : AppDatabase
    lateinit var favoriteFragment: FavoriteFragment

    companion object{
        const val API_KEY = "7519cb3f829ecd53bd9b7007076dbe23"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db = AppDatabase.invoke(this)

        setSupportActionBar(my_toolbar)



        val bottomNavigation : BottomNavigationView = findViewById(R.id.navigationView)
        var status : String = "STATUS_CURRENT"


        if(getFragmentStatus(status) == R.id.navigationPlaying) {
            nowPlayingFragment = NowPlayingFragment(false, db)
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame_layout, nowPlayingFragment,"NOW_PLAYING")
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()
        } else if(getFragmentStatus(status) == R.id.navigationRating) {
            topRatingFragment = TopRatingFragment(false, db)
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame_layout, topRatingFragment, "TOP_RATING")
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()
        } else if(getFragmentStatus(status) == R.id.navigationFavorite) {
            favoriteFragment = FavoriteFragment(false,db)
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame_layout, favoriteFragment, "FAVORITE")
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()

        } else {
            nowPlayingFragment = NowPlayingFragment(false, db)
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame_layout, nowPlayingFragment,"NOW_PLAYING")
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()
        }




        bottomNavigation.setOnNavigationItemSelectedListener { item ->  
            when(item.itemId) {
                // now create three fragments
                R.id.navigationPlaying -> {
                    storeFragmentStatus(status, R.id.navigationPlaying)

                    nowPlayingFragment = NowPlayingFragment(false, db)
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, nowPlayingFragment,"NOW_PLAYING")
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }

                R.id.navigationRating -> {
                    storeFragmentStatus(status, R.id.navigationRating)

                    topRatingFragment = TopRatingFragment(false, db)
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, topRatingFragment, "TOP_RATING")
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }

                R.id.navigationFavorite -> {
                    storeFragmentStatus(status, R.id.navigationFavorite)
                    favoriteFragment = FavoriteFragment(false,db)
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, favoriteFragment, "FAVORITE")
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }

            }
            true
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
               val nowPlaying = supportFragmentManager.findFragmentByTag("NOW_PLAYING")
               if (nowPlaying != null && nowPlaying.isVisible) {
                   nowPlayingFragment = NowPlayingFragment(true, db)
                   supportFragmentManager
                       .beginTransaction()
                       .replace(R.id.frame_layout, nowPlayingFragment,"NOW_PLAYING")
                       .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                       .commit()
               }
                val topRating = supportFragmentManager.findFragmentByTag("TOP_RATING")
                if (topRating != null && topRating.isVisible) {
                    topRatingFragment = TopRatingFragment(true, db)
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, topRatingFragment,"TOP_RATING")
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
                val favorite = supportFragmentManager.findFragmentByTag("FAVORITE")
                if(favorite != null && favorite.isVisible) {
                    favoriteFragment = FavoriteFragment(true,db)
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, favoriteFragment, "FAVORITE")
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
            }

            R.id.button_list -> {
                val myFragment = supportFragmentManager.findFragmentByTag("NOW_PLAYING")
                if (myFragment != null && myFragment.isVisible) {
                    nowPlayingFragment = NowPlayingFragment(false, db)
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, nowPlayingFragment,"NOW_PLAYING")
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
                val topRating = supportFragmentManager.findFragmentByTag("TOP_RATING")
                if (topRating != null && topRating.isVisible) {
                    topRatingFragment = TopRatingFragment(false, db)
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, topRatingFragment,"TOP_RATING")
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
                val favorite = supportFragmentManager.findFragmentByTag("FAVORITE")
                if(favorite != null && favorite.isVisible) {
                    favoriteFragment = FavoriteFragment(false,db)
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, favoriteFragment, "FAVORITE")
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
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

    private fun storeFragmentStatus(STATUS: String, idStatus: Int) {
        val sharedPreferences = this.getSharedPreferences("STATUS_FRAGMENT", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt(STATUS, idStatus)
        editor.apply();
    }

    private fun getFragmentStatus(STATUS: String): Int {
        val sharedPreferences = this.getSharedPreferences("STATUS_FRAGMENT", Context.MODE_PRIVATE)
        return sharedPreferences.getInt(STATUS, -1)
    }

    fun getDataFromApi(){
        MovieService.getInstance().getApi().getNowPlaying().enqueue(object :
            Callback<VideoResponse> {
            override fun onFailure(call: Call<VideoResponse>?, t: Throwable?) {
                //todo something
            }

            override fun onResponse(
                call: Call<VideoResponse>?,
                response: Response<VideoResponse>?
            ) {
                response?.let {
                    val resp = it.body()
                    resp.results
                }
            }
        })
    }
}
