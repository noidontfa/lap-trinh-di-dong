package com.example.week4


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
    lateinit var nowPlayingFragment: NowPlayingFragment
//    lateinit var myFavoriteFragment: MyFavoriteFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(my_toolbar)



        val bottomNavigation : BottomNavigationView = findViewById(R.id.navigationView)

        nowPlayingFragment = NowPlayingFragment(false)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout, nowPlayingFragment,"NOW_PLAYING")
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()


        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                // now create three fragments
                R.id.navigationPlaying -> {

                    nowPlayingFragment = NowPlayingFragment(false)
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, nowPlayingFragment,"NOW_PLAYING")
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }

                R.id.navigationRating -> {

                    topRatingFragment = TopRatingFragment(false)
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, topRatingFragment, "TOP_RATING")
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }

//                R.id.navigationFavorite -> {
//
//                    myFavoriteFragment = MyFavoriteFragment()
//                    supportFragmentManager
//                        .beginTransaction()
//                        .replace(R.id.frame_layout, myFavoriteFragment)
//                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                        .commit()
//                }
            }
            true
        }

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
               val nowPlaying = supportFragmentManager.findFragmentByTag("NOW_PLAYING")
               if (nowPlaying != null && nowPlaying.isVisible) {
                   nowPlayingFragment = NowPlayingFragment(true)
                   supportFragmentManager
                       .beginTransaction()
                       .replace(R.id.frame_layout, nowPlayingFragment,"NOW_PLAYING")
                       .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                       .commit()
               }
                val topRating = supportFragmentManager.findFragmentByTag("TOP_RATING")
                if (topRating != null && topRating.isVisible) {
                    topRatingFragment = TopRatingFragment(true)
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, topRatingFragment,"TOP_RATING")
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
            }

            R.id.button_list -> {
                val myFragment = supportFragmentManager.findFragmentByTag("NOW_PLAYING")
                if (myFragment != null && myFragment.isVisible) {
                    nowPlayingFragment = NowPlayingFragment(false)
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, nowPlayingFragment,"NOW_PLAYING")
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
                val topRating = supportFragmentManager.findFragmentByTag("TOP_RATING")
                if (topRating != null && topRating.isVisible) {
                    topRatingFragment = TopRatingFragment(false)
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, topRatingFragment,"TOP_RATING")
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

}
