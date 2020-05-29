package com.example.week4

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_top_rating.*

/**
 * A simple [Fragment] subclass.
 */
class TopRatingFragment(private val gridview : Boolean, var db : AppDatabase) : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top_rating, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter : MovieAdapter
        if(gridview) {
            adapter = MovieAdapter(ctx = activity as Context, movies = MovieModel.getTopRateMovieObject(), type = 1, database = db)
            rvTopRatingFragment.layoutManager = GridLayoutManager(activity,3)
        } else {
            adapter = MovieAdapter(ctx = activity as Context, movies = MovieModel.getTopRateMovieObject(), type = 0, database = db)
            rvTopRatingFragment.layoutManager = LinearLayoutManager(activity)
        }
        rvTopRatingFragment.adapter = adapter
        adapter.listener = object: MovieAdapter.MovieListener{
            override fun onClickListener(movie: MovieModel.Content) {
                startDetailScreen(movie)
            }
        }
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean{

        var itemview = item.itemId
        when(itemview)
        {
            R.id.button_grid -> {
                val Count: Int = 3
                val adapter = MovieAdapter(ctx = activity as Context, movies = MovieModel.getTopRateMovieObject() , type = 1, database = db)
                rvTopRatingFragment.adapter = adapter
                rvTopRatingFragment.layoutManager = GridLayoutManager(activity, Count)
                adapter.listener = object: MovieAdapter.MovieListener{
                    override fun onClickListener(movie: MovieModel.Content) {
                        startDetailScreen(movie)
                    }
                }
            }

            R.id.button_list -> {
                val adapter = MovieAdapter(ctx = activity as Context, movies = MovieModel.getTopRateMovieObject() , type = 0, database = db)
                rvTopRatingFragment.adapter = adapter
                rvTopRatingFragment.layoutManager = LinearLayoutManager(activity)
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
        val intent: Intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra(MOVIE_TITLE_KEY, movie.title)
        intent.putExtra(MOVIE_DESCRIPTION_KEY, movie.overview)
        intent.putExtra(MOVIE_IMAGE_POSTER_KEY, movie.backdrop_path)
        startActivity(intent)
    }
}
