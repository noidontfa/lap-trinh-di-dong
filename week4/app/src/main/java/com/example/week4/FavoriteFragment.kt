package com.example.week4

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_now_playing.*

class FavoriteFragment(private val gridLayout : Boolean, var db: AppDatabase) : Fragment() {

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
            adapter = MovieAdapter(ctx = activity as Context, movies = parseMovieToMovieModel(db.movieDAO().getAll()), type = 1, database = db)
            rvNowPlayingFragment.layoutManager = GridLayoutManager(activity,3)
        } else {
            adapter = MovieAdapter(ctx = activity as Context, movies = parseMovieToMovieModel(db.movieDAO().getAll()), type = 0, database = db)
            rvNowPlayingFragment.layoutManager = LinearLayoutManager(activity)

        }
        rvNowPlayingFragment.adapter = adapter
        adapter.listener = object: MovieAdapter.MovieListener{
            override fun onClickListener(movie: Video) {
                startDetailScreen(movie)
            }
        }
    }

    private fun startDetailScreen(movie: Video) {
        val intent: Intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra(MOVIE_TITLE_KEY, movie.title)
        intent.putExtra(MOVIE_DESCRIPTION_KEY, movie.overview)
        intent.putExtra(MOVIE_IMAGE_POSTER_KEY, movie.backdropPath)
        startActivity(intent)
    }

    private fun parseMovieToMovieModel(movie: List<Movie>) : VideoResponse {
       val m = VideoResponse()
        movie.forEach { e ->
            val content = Video()
            content.id = e.id
            content.overview = e.description
            content.poster_path = e.movie_poster_path
            content.title = e.name
            m.result?.add(content);

        }
        return m
    }
}