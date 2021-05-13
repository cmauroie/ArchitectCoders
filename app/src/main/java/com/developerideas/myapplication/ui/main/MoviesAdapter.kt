package com.developerideas.myapplication.ui.main

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.developerideas.myapplication.R
import com.developerideas.myapplication.databinding.ViewMovieBinding
import com.developerideas.myapplication.model.database.Movie
import com.developerideas.myapplication.ui.common.basicDiffUtil
import com.developerideas.myapplication.ui.common.bindingInflate

class MoviesAdapter(private val listener: (Movie) -> Unit) :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {
    var movies: List<Movie> by basicDiffUtil(
        emptyList(),
        areItemsTheSame = { old, new -> old.id == new.id }
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.bindingInflate(R.layout.view_movie, false))
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        movie.posterPath = "https://image.tmdb.org/t/p/w185/${movie.posterPath}"
        holder.dataBinding.movie = movie
        val average = movie.voteAverage / 2 //score is between 0 and 10
        holder.dataBinding.average = average.toFloat()
        holder.itemView.setOnClickListener { listener(movie) }
    }

    class ViewHolder(val dataBinding: ViewMovieBinding) : RecyclerView.ViewHolder(dataBinding.root) {
        /*private val binding = ViewMovieBinding.bind(view)
        fun bind(movie: Movie) = with(binding) {
            movieTitle.text = movie.title
            movieCover.loadUrl("https://image.tmdb.org/t/p/w185/${movie.posterPath}")
            val average = movie.voteAverage / 2 //score is between 0 and 10
            score.rating = average.toFloat()
        }*/
    }
}