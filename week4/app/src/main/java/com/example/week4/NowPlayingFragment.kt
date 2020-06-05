package com.example.week4

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_now_playing.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass.
 */
class NowPlayingFragment(private val gridLayout : Boolean, var db: AppDatabase) : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_now_playing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        val adapter : MovieAdapter
        if(gridLayout) {
            adapter = MovieAdapter(ctx = activity as Context, movies = MovieModel.getNowPlayingMovieObject(), type = 1, database = db)
            rvNowPlayingFragment.layoutManager = GridLayoutManager(activity,3)
        } else {
            adapter = MovieAdapter(ctx = activity as Context, movies = MovieModel.getNowPlayingMovieObject(), type = 0, database = db)
            rvNowPlayingFragment.layoutManager = LinearLayoutManager(activity)

        }
        rvNowPlayingFragment.adapter = adapter
        adapter.listener = object: MovieAdapter.MovieListener{
            override fun onClickListener(movie: MovieModel.Content) {
                startDetailScreen(movie)
            }
        }
    }

    private fun startDetailScreen(movie: MovieModel.Content) {
        val intent: Intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra(MOVIE_TITLE_KEY, movie.title)
        intent.putExtra(MOVIE_DESCRIPTION_KEY, movie.overview)
        intent.putExtra(MOVIE_IMAGE_POSTER_KEY, movie.backdrop_path)
        startActivity(intent)
    }
}
