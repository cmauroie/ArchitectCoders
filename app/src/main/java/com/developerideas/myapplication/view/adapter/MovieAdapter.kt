package com.developerideas.myapplication.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.developerideas.myapplication.R
import com.developerideas.myapplication.model.Movie

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ViewHolder>(){

    var movieList = ArrayList<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_movie, parent,false))
    }

    override fun onBindViewHolder(holder: MovieAdapter.ViewHolder, position: Int) {
        val movie = movieList[position]

        holder.tvMovieItemTitle.text = movie.originalTitle

    }

    override fun getItemCount() = movieList.size

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val tvMovieItemTitle = itemView.findViewById<TextView>(R.id.tvMovieItemTitle)

    }

    fun updateData(data: List<Movie>){
        movieList.clear()
        movieList.addAll(data)
        notifyDataSetChanged()
    }
}