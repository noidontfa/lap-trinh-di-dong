package com.example.week4

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.bumptech.glide.Glide

class MovieAdapter(val ctx: Context, val movies:VideoResponse, val type: Int, var database: AppDatabase) : RecyclerView.Adapter<MovieAdapter.MovieVH>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieVH {
        var view = LayoutInflater.from(ctx).inflate(R.layout.item_movie, parent,false)
        if (type == 1) {
            view = LayoutInflater.from(ctx).inflate(R.layout.gridview, parent,false)
        }

        return MovieVH(view)
    }

    override fun getItemCount(): Int {
        return movies.result!!.size
        //return movies.results!!.size
    }

    override fun onBindViewHolder(holder: MovieVH, position: Int) {
        var flag : Boolean = false
        val movie = movies.result!![position]
        var favorite_movie = Movie()
        if(type == 1) {
            Glide.with(ctx)
                .load("https://image.tmdb.org/t/p/w500/"+movie.poster_path)
                .into(holder.movie_avatar)
            holder.movie_name.text = movie.title
        } else {
            Glide.with(ctx)
                .load("https://image.tmdb.org/t/p/w500/"+movie.poster_path)
                .into(holder.movie_avatar)
            holder.movie_name.text = movie.title
            holder.movie_desc.text = movie.overview
        }
        holder.itemView.setOnClickListener{
            listener?.onClickListener(movie)
        }
        holder.movie_favorite.setOnClickListener() {

            if (flag == false) {
                AlertDialog.Builder(ctx)
                    .setTitle("Favorite")
                    .setMessage("Do you want to add this movie to Favorite")
                    .setPositiveButton("OK") { dialog, _ ->
                        holder.movie_favorite.setImageResource(R.drawable.ic_favorite_black_24dp)
                        favorite_movie.name = movie.title.toString()
                        favorite_movie.description = movie.overview.toString()
                        favorite_movie.movie_poster_path = movie.poster_path.toString()
                        val fv_movie_DAO = database.movieDAO()
                        //database.movieDAO().insert(favorite_movie)
                        val id = fv_movie_DAO.insert(favorite_movie)
                        favorite_movie.id = id.toInt()
                        dialog.dismiss()
                        flag = true;
                    }
                    .setNegativeButton("Cancel") { dialog, _ ->
                        holder.movie_favorite.setImageResource(R.drawable.ic_favorite_border_black_24dp)
                        dialog.dismiss()
                    }.create().show()
            }
            else{
                holder.movie_favorite.setImageResource(R.drawable.ic_favorite_border_black_24dp)
                database.movieDAO().delete(favorite_movie)
                flag = false
                Toast.makeText(ctx, "Remove from Favorite", Toast.LENGTH_SHORT).show()
            }
        }
    }
    var listener: MovieListener? = null
    interface MovieListener{
        fun onClickListener(movie: Video)
    }

    class MovieVH(itemView: View) : RecyclerView.ViewHolder(itemView){
        val movie_avatar = itemView.findViewById<ImageView>(R.id.imageView2)
        val movie_name = itemView.findViewById<TextView>(R.id.text_view_title)
        val movie_desc = itemView.findViewById<TextView>(R.id.text_view_overview)
        val movie_favorite = itemView.findViewById<ImageView>(R.id.favorite_icon)
    }
}