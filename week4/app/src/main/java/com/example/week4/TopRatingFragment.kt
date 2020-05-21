package com.example.week4

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_top_rating.*

/**
 * A simple [Fragment] subclass.
 */
class TopRatingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        val adapter = MovieAdapter(ctx = this, movies = MovieModel.parseToObject() , type = 0)
//        rvTopRatingFragment.layoutManager = LinearLayoutManager(activity)
//        rvTopRatingFragment.adapter = adapter
//        adapter.listener = object: MovieAdapter.MovieListener{
//            override fun onClickListener(movie: MovieModel.Content) {
//                startDetailScreen(movie)
//            }
//        }
        return inflater.inflate(R.layout.fragment_top_rating, container, false)
    }




//    override fun onOptionsItemSelected(item: MenuItem): Boolean{
//
//        var itemview = item.itemId
//        when(itemview)
//        {
//            R.id.button_grid -> {
//                val Count: Int = 3
//                val adapter = MovieAdapter(ctx = this, movies = MovieModel.parseToObject() , type = 1)
//                rvTopRatingFragment.adapter = adapter
//                rvTopRatingFragment.layoutManager = GridLayoutManager(activity, Count)
//                adapter.listener = object: MovieAdapter.MovieListener{
//                    override fun onClickListener(movie: MovieModel.Content) {
//                        startDetailScreen(movie)
//                    }
//                }
//            }
//
//            R.id.button_list -> {
//                val adapter = MovieAdapter(ctx = this, movies = MovieModel.parseToObject() , type = 0)
//                rvTopRatingFragment.adapter = adapter
//                rvTopRatingFragment.layoutManager = LinearLayoutManager(activity)
//                adapter.listener = object: MovieAdapter.MovieListener{
//                    override fun onClickListener(movie: MovieModel.Content) {
//                        startDetailScreen(movie)
//                    }
//                }
//            }
//        }
//        return false
//    }
//
//    private fun startDetailScreen(movie: MovieModel.Content) {
//        val intent: Intent = Intent(activity, DetailActivity::class.java)
//        intent.putExtra(MOVIE_TITLE_KEY, movie.title)
//        intent.putExtra(MOVIE_DESCRIPTION_KEY, movie.overview)
//        intent.putExtra(MOVIE_IMAGE_POSTER_KEY, movie.backdrop_path)
//        startActivity(intent)
//    }
}
