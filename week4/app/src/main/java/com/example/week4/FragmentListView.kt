package com.example.week4

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.item_movie.*
import kotlinx.android.synthetic.main.pragment_listview.*

class FragmentListView : BaseFragment() {

//    private val movie : MovieModel.Result = MovieModel.parseToObject()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.e(getLoggerTag(), "OnCreateView")

        val view :View = inflater.inflate(R.layout.pragment_listview, container, false)
        return view
    }
    override fun getLoggerTag(): String {
       return FragmentListView::class.java.simpleName
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = MovieAdapter(ctx = activity as Context, movies = MovieModel.getNowPlayingMovieObject() , type = 0)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(activity)


        adapter.listener = object: MovieAdapter.MovieListener{
            override fun onClickListener(movie: MovieModel.Content) {

            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }


}