package com.example.week3

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextClock
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieAdapter(val ctx : Context, val movies:MovieModel.Result) : RecyclerView.Adapter<MovieAdapter.MovieVH>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieVH {
        val view = LayoutInflater.from(ctx).inflate(R.layout.item_movie, parent,false)
        return MovieVH(view)
    }

    override fun getItemCount(): Int {
        return movies.results.size
    }

    override fun onBindViewHolder(holder: MovieVH, position: Int) {
        val movie = movies.results[position]
        Glide.with(ctx)
            .load("https://image.tmdb.org/t/p/w500/"+movie.poster_path)
            .centerCrop()
            .into(holder.movie_avatar)
        holder.movie_name.text = movie.title
        holder.movie_desc.text = movie.overview
        holder.itemView.setOnClickListener{
            listener?.onClickListener(movie)
        }
    }

    var listener: MovieListener? = null

    interface MovieListener{
        fun onClickListener(movie: MovieModel.Content)
    }

    class MovieVH(itemView: View) : RecyclerView.ViewHolder(itemView){
        val movie_avatar = itemView.findViewById<ImageView>(R.id.imageView2)
        val movie_name = itemView.findViewById<TextView>(R.id.text_view_title)
        val movie_desc = itemView.findViewById<TextView>(R.id.text_view_overview)
    }
}